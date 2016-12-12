package com.ibm.santander.controller;

import com.ibm.santander.model.Conta;

import com.ibm.santander.service.ContaService;

import java.math.BigDecimal;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class MainController {
 
 	@Autowired
	private ContaService contaService;
	
	@RequestMapping(value="/origem", method=RequestMethod.GET)
	public ModelAndView informarOrigem(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView model = null;
    	
		model = new ModelAndView("passo1");
		
		Conta conta = new Conta();
		
		model.addObject("conta", conta);

        return model;
		
	}
	
	@RequestMapping(value="/salvarOrigem", method=RequestMethod.POST)
	public String salvarOrigem(	HttpServletRequest request,
								HttpServletResponse response, 
								HttpSession session,
								@ModelAttribute("conta") Conta pConta)
	{
		Conta conta = null;
		
		try
		{
			
			conta = contaService.isCadastrada(pConta.getAgencia(), pConta.getNumero());
						
			if (conta != null)
			{
			
				if (contaService.isSaldoPositivo(conta, pConta.getValorTransferir())){
				
					session.setAttribute("idOrigem", conta.getId());
					session.setAttribute("valorTransferir", pConta.getValorTransferir());
					return "redirect:/destino";
				
				}
				else
				{
					return "redirect:/origem";
				}
			}
			else
			{	
				return "redirect:/origem";
			}

		}
		catch(Exception e)
		{
			e.printStackTrace();
			return "redirect:/origem";
		}
		
	}
	
	@RequestMapping(value="/destino", method=RequestMethod.GET)
	public ModelAndView informarDestino(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView model = null;
    	
		model = new ModelAndView("passo2");
		
		Conta conta = new Conta();
		
		model.addObject("conta", conta);

        return model;
		
	}
	
	@RequestMapping(value="/salvarDestino", method=RequestMethod.POST)
	public String salvarDestino(HttpServletRequest request,
								HttpServletResponse response, 
								HttpSession session,
								@ModelAttribute("conta") Conta pConta)
	{
		Conta conta = null;
		
		try
		{
			
			conta = contaService.isCadastrada(pConta.getAgencia(), pConta.getNumero());
			
			if (conta != null)
			{
				session.setAttribute("idDestino", conta.getId());
				return "redirect:/transferir";
			}
			else
			{
				//model = new ModelAndView("hostessLogonForm");
				//model.addObject("credential", credential);
				return "redirect:/destino";
			}

		}
		catch(Exception e)
		{
			e.printStackTrace();
			return "redirect:/destino";
		}
		
	}
	
	
	@RequestMapping(value="/transferir", method=RequestMethod.GET)
	public ModelAndView transferir(HttpServletRequest request, HttpServletResponse response, HttpSession session)
	{
		ModelAndView model = null;
		
		int idOrigem  = Integer.parseInt(session.getAttribute("idOrigem").toString());
		int idDestino = Integer.parseInt(session.getAttribute("idDestino").toString());
		BigDecimal valorTransferir = new BigDecimal(session.getAttribute("valorTransferir").toString());
		
		contaService.depositar(idDestino, contaService.sacar(idOrigem, valorTransferir));
    	
		model = new ModelAndView("passo3");
		
        return model;
		
	}
	
}