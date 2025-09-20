package br.com.empresa.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/produto")
public class ProdutoController {


    @GetMapping("/home")
    public String home(){

        return "produtos";
    }


}
