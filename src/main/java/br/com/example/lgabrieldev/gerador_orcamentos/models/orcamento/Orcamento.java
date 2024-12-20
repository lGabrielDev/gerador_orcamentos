package br.com.example.lgabrieldev.gerador_orcamentos.models.orcamento;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import br.com.example.lgabrieldev.gerador_orcamentos.converter_items.ConverterItems;
import br.com.example.lgabrieldev.gerador_orcamentos.models.item.Item;

public class Orcamento {
    
    //attributes
    private Long id;
    private LocalDate dataEnvio;
    private String cliente;
    private String descricao;
    private String observacoes;
    private List<Item> items;
    private Integer prazoEntrega;
    private Double sinal;
    private Integer parcelas;

    //constructors
    public Orcamento(OrcamentoCreate orcamentoCreate) {
        this.dataEnvio = LocalDate.now();
        this.cliente = orcamentoCreate.getCliente();
        this.descricao = orcamentoCreate.getDescricao();
        this.observacoes = orcamentoCreate.getObservacoes();
        this.prazoEntrega = orcamentoCreate.getPrazoEntrega();
        this.sinal = orcamentoCreate.getSinal();
        this.parcelas = orcamentoCreate.getParcelas();

        ConverterItems converterItems = new ConverterItems();
        this.items = converterItems.converterItems(orcamentoCreate.getItems());
    }

    //getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDataEnvio() {
        DateTimeFormatter formatacaoPadrao = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return formatacaoPadrao.format(this.dataEnvio);
    }

    public void setDataEnvio(LocalDate dataEnvio) {
        this.dataEnvio = dataEnvio;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public Integer getPrazoEntrega() {
        return this.prazoEntrega;
    }

    public void setPrazoEntrega(Integer prazoEntrega) {
        this.prazoEntrega = prazoEntrega;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Double getSinal() {
        if(this.sinal == null){
            this.sinal = 0d;
        }
        return sinal;
    }

    public void setSinal(Double sinal) {
        this.sinal = sinal;
    }

    //calculos do total
    public Double getValorTotal(){
        
        Double total = this.items.stream().mapToDouble((item) -> item.getValorTotal()).sum();
        return total;
    }

    public Integer getParcelas() {
        if(this.parcelas == null){
            this.parcelas = 0;
        }
        
        return this.parcelas;
    }

    public void setParcelas(Integer parcelas) {
        this.parcelas = parcelas;
    }

    public Double getCalcularParcela(){
        return (this.getValorTotal() - this.getSinal()) / this.parcelas;
    }
}