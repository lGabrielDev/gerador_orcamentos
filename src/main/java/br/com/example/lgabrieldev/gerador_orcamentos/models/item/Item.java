package br.com.example.lgabrieldev.gerador_orcamentos.models.item;

public class Item {
    
    //attributes
    private Long id;
    private String descricao;
    private String acabamento;
    private String medidas;
    private Double valorUnit;
    private Integer quantity;
    
    //constructors
    public Item(String descricao, String acabamento, String medidas, Double valorUnit, Integer quantity) {
        this.descricao = descricao;
        this.acabamento = acabamento;
        this.medidas = medidas;
        this.valorUnit = valorUnit;
        this.quantity = quantity;  
    }

    //getters and setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public String getAcabamento() {
        if(this.acabamento == null){
            this.acabamento = "";
        }
        return acabamento;
    }
    public void setAcabamento(String acabamento) {
        this.acabamento = acabamento;
    }
    public String getMedidas() {
        if(this.medidas == null){
            this.acabamento = "";
        }
        return medidas;
    }
    public void setMedidas(String medidas) {
        this.medidas = medidas;
    }
    public Double getValorUnit() {
        return valorUnit;
    }
    public void setValorUnit(Double valorUnit) {
        this.valorUnit = valorUnit;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getValorTotal() {
        return this.valorUnit * this.quantity;
    }

    public String getDescricaoAndAcabamentoAndMedidas(){

        if(this.medidas != null && this.acabamento != null){
            return String.format(
                "%s\n" +
                "%s\n\n" +
                "Medidas: %s", this.descricao, this.acabamento, this.medidas
            );
        }
        else if(this.medidas == null && this.acabamento == null){
            return String.format(
                "%s", this.descricao
            );
        }
        else if(this.medidas == null && this.acabamento != null){
            return String.format(
                "%s\n" +
                "%s", this.descricao, this.acabamento
            );
        }
        else{
            return String.format(
                "%s\n" +
                "Medidas: %s", this.descricao, this.medidas
            );
        }
    }
}
