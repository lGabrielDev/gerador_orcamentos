package br.com.example.lgabrieldev.gerador_orcamentos;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.example.lgabrieldev.gerador_orcamentos.models.orcamento.OrcamentoCreate;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/orcamento")
@Tag(name = "orcamento", description = "rotas para manipular orcamentos")
public class OrcamentoController {
    
    //attributes
    private OrcamentoService orcamentoService;

    //constructors
    public OrcamentoController(OrcamentoService orcamentoService){
        this.orcamentoService = orcamentoService;
    }

    // ================= POST =================
    @Operation(description = "Cria um orcamento e o envia pelo WhatsApp")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200 OK", description = "Orcamento criado e enviado com sucesso!"),
            @ApiResponse(responseCode = "401 CONFLICT", description = "Não foi possível criar orcamento porque o arquivo modelo está aberto. Feche o arquivo."),
            @ApiResponse(responseCode = "226 IM USED", description = "Existe outro navegador chrome aberto. Isso acontece quando voce ainda nao autenticou o QRCOde no WhatsApp. Ao passar os 15 segundo, a aplicacao é encerrada mas o navegador continua aberto. Voce precisa fechá-lo.")
        }
    )
    @PostMapping("")
    public ResponseEntity<String> createOrcamento(@RequestBody OrcamentoCreate orcamentoCreate){
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(this.orcamentoService.createOrcamento(orcamentoCreate));
    }
}