
package it.univpm.demoSpringBootApp.Metadati;

/** Classe che si occupa dei metadati con i rispettivi get e set

 * @author Federico Tartabini

 * @author Nicola Montesi

 */

public class MetaDati
{private String nome, tipo, campo;

public MetaDati() {	}

	public String getNome() {
	return nome;
}

public void setNome(String nome) {
	this.nome = nome;
}

public String getTipo() {
	return tipo;
}

public void setTipo(String tipo) {
	this.tipo = tipo;
}

public String getCampo() {
	return campo;
}

public void setCampo(String campo) {
	this.campo = campo;
}

	public String toString() {

		return "MetaDati [toString()=" + super.toString() + "]";}}

