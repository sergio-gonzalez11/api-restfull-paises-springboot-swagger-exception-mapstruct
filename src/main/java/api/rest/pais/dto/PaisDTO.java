package api.rest.pais.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Pais")  
public class PaisDTO {

	@ApiModelProperty(value = "Identificación abrebriada del pais", dataType = "String")
	private String codigo;
	
	@ApiModelProperty(value = "Nombre del pais", dataType = "String")
	private String nombre;
	
	@ApiModelProperty(value = "Moneda identificativa del pais", dataType = "String")
	private String moneda;
	
	@ApiModelProperty(value = "Símbolo de la moneda", dataType = "String")
	private String simbolo;
	
	@ApiModelProperty(value = "Url de enlace del pais", dataType = "String")
	private String bandera;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getMoneda() {
		return moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	public String getSimbolo() {
		return simbolo;
	}

	public void setSimbolo(String simbolo) {
		this.simbolo = simbolo;
	}

	public String getBandera() {
		return bandera;
	}

	public void setBandera(String bandera) {
		this.bandera = bandera;
	}

}
