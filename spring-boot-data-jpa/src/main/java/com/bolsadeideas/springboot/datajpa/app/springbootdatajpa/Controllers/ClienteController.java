package com.bolsadeideas.springboot.datajpa.app.springbootdatajpa.Controllers;

import java.util.Map;

import javax.validation.Valid;

import com.bolsadeideas.springboot.datajpa.app.springbootdatajpa.Models.Dao.IClienteDao;
import com.bolsadeideas.springboot.datajpa.app.springbootdatajpa.Models.Entity.Cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ClienteController {
    
    @Autowired//Estamos conectandolo al Query (Consulta) para generar una vista 
    @Qualifier("ClienteDaoJPA")//Identifica cual repositorio usar
    private IClienteDao clienteDao;//Instancia 


    @GetMapping("/listar")
    public String listar(Model model){ //Asigno información a los templates, con los models

        model.addAttribute("titulo", "Listado de clientes");
        model.addAttribute("clientes", clienteDao.findAll());

        return "listar";
    }

    @GetMapping("/form")
    public String crear(Map<String,Object> model){//El String es lo que le vamos a enviar

        Cliente cliente = new Cliente(); //Instanciamos el objeto

        model.put("cliente", cliente); //El primero es como se encuentra en el template (nombre de la variable en el html), y el otro es el objeto, con este le enviamos de otra forma
        model.put("titulo", "Formulario de Cliente"); 

        return "form";
    }

    //@PostMapping("/form") para guardar desde un formulario se utiliza el Post
    @RequestMapping(value = "/form", method = RequestMethod.POST) //Hay que especificar ya que por predeterminado pone GET
    public String guardar(@Valid Cliente cliente,BindingResult result, Model model){ //Estamos validando al cliente ---- debemos entrar al pom.xml y duplicar la última dependencia y quitarle el Scope y cambiar el test por -validation

        clienteDao.save(cliente);//VAmos a guardar el cliente 

        return "redirect:listar"; //Una vez que guarda nos manda nuevamente a listar
    }

}
