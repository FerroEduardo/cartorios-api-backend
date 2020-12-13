package com.ferroeduardo.cartorios_api_backend.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "cartorios_api_dados")
public class Cartorio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "uf", length = 2)
    private String uf;

    @Column(name = "cnpj", length = 18)
    private String cnpj;

    @Column(name = "nome_oficial", length = 150)
    private String nomeOficial;

    @Column(name = "nome_fantasia", length = 150)
    private String nomeFantasia;

    @Column(name = "endereco", length = 120)
    private String endereco;

    @Column(name = "bairro", length = 50)
    private String bairro;

    @Column(name = "municipio", length = 50)
    private String municipio;

    @Column(name = "cep", length = 9)
    private String cep;

    @Column(name = "nome_do_titular", length = 110)
    private String nomeDoTitular;

    @Column(name = "nome_do_substituto", length = 110)
    private String nomeDoSubstituto;

    @Column(name = "nome_do_juiz", length = 110)
    private String nomeDoJuiz;

    @Column(name = "homepage", length = 90)
    private String homepage;

    @Column(name = "email", length = 120)
    private String email;

    @Column(name = "telefone", length = 40)
    private String telefone;

    @Column(name = "fax", length = 40)
    private String fax;

    @Column(name = "horario_de_funcionamento", length = 150)
    private String horarioDeAtendimento;

    @Column(name = "area_de_abrangencia", length = 720)
    private String areaDeAbrangencia;

    @Column(name = "atribuicoes", length = 550)
    private String atribuicoes;

    public Cartorio(Long id, String uf, String cnpj, String nomeOficial, String nomeFantasia, String endereco, String bairro, String municipio, String cep, String nomeDoTitular, String nomeDoSubstituto, String nomeDoJuiz, String homepage, String email, String telefone, String fax, String horarioDeAtendimento, String areaDeAbrangencia, String atribuicoes) {
        this.id = id;
        this.uf = uf;
        this.cnpj = cnpj;
        this.nomeOficial = nomeOficial;
        this.nomeFantasia = nomeFantasia;
        this.endereco = endereco;
        this.bairro = bairro;
        this.municipio = municipio;
        this.cep = cep;
        this.nomeDoTitular = nomeDoTitular;
        this.nomeDoSubstituto = nomeDoSubstituto;
        this.nomeDoJuiz = nomeDoJuiz;
        this.homepage = homepage;
        this.email = email;
        this.telefone = telefone;
        this.fax = fax;
        this.horarioDeAtendimento = horarioDeAtendimento;
        this.areaDeAbrangencia = areaDeAbrangencia;
        this.atribuicoes = atribuicoes;
    }

    public Cartorio(String uf, String cnpj, String nomeOficial, String nomeFantasia, String endereco, String bairro, String municipio, String cep, String nomeDoTitular, String nomeDoSubstituto, String nomeDoJuiz, String homepage, String email, String telefone, String fax, String horarioDeAtendimento, String areaDeAbrangencia, String atribuicoes) {
        this.uf = uf;
        this.cnpj = cnpj;
        this.nomeOficial = nomeOficial;
        this.nomeFantasia = nomeFantasia;
        this.endereco = endereco;
        this.bairro = bairro;
        this.municipio = municipio;
        this.cep = cep;
        this.nomeDoTitular = nomeDoTitular;
        this.nomeDoSubstituto = nomeDoSubstituto;
        this.nomeDoJuiz = nomeDoJuiz;
        this.homepage = homepage;
        this.email = email;
        this.telefone = telefone;
        this.fax = fax;
        this.horarioDeAtendimento = horarioDeAtendimento;
        this.areaDeAbrangencia = areaDeAbrangencia;
        this.atribuicoes = atribuicoes;
    }

    public Cartorio() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNomeOficial() {
        return nomeOficial;
    }

    public void setNomeOficial(String nomeOficial) {
        this.nomeOficial = nomeOficial;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getNomeDoTitular() {
        return nomeDoTitular;
    }

    public void setNomeDoTitular(String nomeDoTitular) {
        this.nomeDoTitular = nomeDoTitular;
    }

    public String getNomeDoSubstituto() {
        return nomeDoSubstituto;
    }

    public void setNomeDoSubstituto(String nomeDoSubstituto) {
        this.nomeDoSubstituto = nomeDoSubstituto;
    }

    public String getNomeDoJuiz() {
        return nomeDoJuiz;
    }

    public void setNomeDoJuiz(String nomeDoJuiz) {
        this.nomeDoJuiz = nomeDoJuiz;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getHorarioDeAtendimento() {
        return horarioDeAtendimento;
    }

    public void setHorarioDeAtendimento(String horarioDeAtendimento) {
        this.horarioDeAtendimento = horarioDeAtendimento;
    }

    public String getAreaDeAbrangencia() {
        return areaDeAbrangencia;
    }

    public void setAreaDeAbrangencia(String areaDeAbrangencia) {
        this.areaDeAbrangencia = areaDeAbrangencia;
    }

    public String getAtribuicoes() {
        return atribuicoes;
    }

    public void setAtribuicoes(String atribuicoes) {
        this.atribuicoes = atribuicoes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cartorio cartorio = (Cartorio) o;
        return Objects.equals(id, cartorio.id) &&
                uf.equals(cartorio.uf) &&
                cnpj.equals(cartorio.cnpj) &&
                Objects.equals(nomeOficial, cartorio.nomeOficial) &&
                Objects.equals(nomeFantasia, cartorio.nomeFantasia) &&
                Objects.equals(endereco, cartorio.endereco) &&
                Objects.equals(bairro, cartorio.bairro) &&
                Objects.equals(municipio, cartorio.municipio) &&
                Objects.equals(cep, cartorio.cep) &&
                Objects.equals(nomeDoTitular, cartorio.nomeDoTitular) &&
                Objects.equals(nomeDoSubstituto, cartorio.nomeDoSubstituto) &&
                Objects.equals(nomeDoJuiz, cartorio.nomeDoJuiz) &&
                Objects.equals(homepage, cartorio.homepage) &&
                Objects.equals(email, cartorio.email) &&
                Objects.equals(telefone, cartorio.telefone) &&
                Objects.equals(fax, cartorio.fax) &&
                Objects.equals(horarioDeAtendimento, cartorio.horarioDeAtendimento) &&
                Objects.equals(areaDeAbrangencia, cartorio.areaDeAbrangencia) &&
                Objects.equals(atribuicoes, cartorio.atribuicoes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, uf, cnpj, nomeOficial, nomeFantasia, endereco, bairro, municipio, cep, nomeDoTitular, nomeDoSubstituto, nomeDoJuiz, homepage, email, telefone, fax, horarioDeAtendimento, areaDeAbrangencia, atribuicoes);
    }

    @Override
    public String toString() {
        return "Cartorio{" +
                "id=" + id +
                ", uf='" + uf + '\'' +
                ", cnpj='" + cnpj + '\'' +
                ", nomeOficial='" + nomeOficial + '\'' +
                ", nomeFantasia='" + nomeFantasia + '\'' +
                ", endereco='" + endereco + '\'' +
                ", bairro='" + bairro + '\'' +
                ", municipio='" + municipio + '\'' +
                ", cep='" + cep + '\'' +
                ", nomeDoTitular='" + nomeDoTitular + '\'' +
                ", nomeDoSubstituto='" + nomeDoSubstituto + '\'' +
                ", nomeDoJuiz='" + nomeDoJuiz + '\'' +
                ", homepage='" + homepage + '\'' +
                ", email='" + email + '\'' +
                ", telefone='" + telefone + '\'' +
                ", fax='" + fax + '\'' +
                ", horarioDeAtendimento='" + horarioDeAtendimento + '\'' +
                ", areaDeAbrangencia='" + areaDeAbrangencia + '\'' +
                ", atribuicoes='" + atribuicoes + '\'' +
                '}';
    }
}
