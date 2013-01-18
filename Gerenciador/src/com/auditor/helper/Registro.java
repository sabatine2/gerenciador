package com.auditor.helper;

public class Registro {  
  
    private String seq;  
    private String origem;  
    private String descricao;//combobox
    private String dataInicio;  
    private String dataFim;  
    private String terminalDestino;
    private String codServico;  
    private String localDestino;  
    private String horaInicio;
    private String horaFim;
    private String complemento;  
    private String pais;  
    private String quantidade;
    private String unidade; 
    private String imp; //valor
    // ...  
  
    public Registro() {}  
  
    public Registro(String[] registro) {  
    	
    	    if(registro.length > 0){
                seq = registro[0];  
                origem = registro[1];  
                descricao = registro[2]; 
                dataInicio = registro[3];  
                dataFim = registro[4];  
                terminalDestino = registro[5];
                codServico = registro[6];  
                localDestino = registro[7];  
                horaInicio = registro[8];
                horaFim = registro[9];
                complemento = registro[10];  
                pais = registro[11];  
                quantidade = registro[12];
                unidade = registro[13];
                imp = registro[14];
            }  
      
     }

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Registro [seq=" + seq + ", origem=" + origem + ", descricao="
				+ descricao + ", dataInicio=" + dataInicio + ", dataFim="
				+ dataFim + ", terminalDestino=" + terminalDestino
				+ ", codServico=" + codServico + ", localDestino="
				+ localDestino + ", horaInicio=" + horaInicio + ", horaFim="
				+ horaFim + ", complemento=" + complemento + ", pais=" + pais
				+ ", quantidade=" + quantidade + ", unidade=" + unidade
				+ ", imp=" + imp + "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((codServico == null) ? 0 : codServico.hashCode());
		result = prime * result
				+ ((complemento == null) ? 0 : complemento.hashCode());
		result = prime * result + ((dataFim == null) ? 0 : dataFim.hashCode());
		result = prime * result
				+ ((dataInicio == null) ? 0 : dataInicio.hashCode());
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((horaFim == null) ? 0 : horaFim.hashCode());
		result = prime * result
				+ ((horaInicio == null) ? 0 : horaInicio.hashCode());
		result = prime * result + ((imp == null) ? 0 : imp.hashCode());
		result = prime * result
				+ ((localDestino == null) ? 0 : localDestino.hashCode());
		result = prime * result + ((origem == null) ? 0 : origem.hashCode());
		result = prime * result + ((pais == null) ? 0 : pais.hashCode());
		result = prime * result
				+ ((quantidade == null) ? 0 : quantidade.hashCode());
		result = prime * result + ((seq == null) ? 0 : seq.hashCode());
		result = prime * result
				+ ((terminalDestino == null) ? 0 : terminalDestino.hashCode());
		result = prime * result + ((unidade == null) ? 0 : unidade.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Registro other = (Registro) obj;
		if (codServico == null) {
			if (other.codServico != null)
				return false;
		} else if (!codServico.equals(other.codServico))
			return false;
		if (complemento == null) {
			if (other.complemento != null)
				return false;
		} else if (!complemento.equals(other.complemento))
			return false;
		if (dataFim == null) {
			if (other.dataFim != null)
				return false;
		} else if (!dataFim.equals(other.dataFim))
			return false;
		if (dataInicio == null) {
			if (other.dataInicio != null)
				return false;
		} else if (!dataInicio.equals(other.dataInicio))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (horaFim == null) {
			if (other.horaFim != null)
				return false;
		} else if (!horaFim.equals(other.horaFim))
			return false;
		if (horaInicio == null) {
			if (other.horaInicio != null)
				return false;
		} else if (!horaInicio.equals(other.horaInicio))
			return false;
		if (imp == null) {
			if (other.imp != null)
				return false;
		} else if (!imp.equals(other.imp))
			return false;
		if (localDestino == null) {
			if (other.localDestino != null)
				return false;
		} else if (!localDestino.equals(other.localDestino))
			return false;
		if (origem == null) {
			if (other.origem != null)
				return false;
		} else if (!origem.equals(other.origem))
			return false;
		if (pais == null) {
			if (other.pais != null)
				return false;
		} else if (!pais.equals(other.pais))
			return false;
		if (quantidade == null) {
			if (other.quantidade != null)
				return false;
		} else if (!quantidade.equals(other.quantidade))
			return false;
		if (seq == null) {
			if (other.seq != null)
				return false;
		} else if (!seq.equals(other.seq))
			return false;
		if (terminalDestino == null) {
			if (other.terminalDestino != null)
				return false;
		} else if (!terminalDestino.equals(other.terminalDestino))
			return false;
		if (unidade == null) {
			if (other.unidade != null)
				return false;
		} else if (!unidade.equals(other.unidade))
			return false;
		return true;
	}

	
	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	public String getOrigem() {
		return origem;
	}

	public void setOrigem(String origem) {
		this.origem = origem;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(String dataInicio) {
		this.dataInicio = dataInicio;
	}

	public String getDataFim() {
		return dataFim;
	}

	public void setDataFim(String dataFim) {
		this.dataFim = dataFim;
	}

	public String getTerminalDestino() {
		return terminalDestino;
	}

	public void setTerminalDestino(String terminalDestino) {
		this.terminalDestino = terminalDestino;
	}

	public String getCodServico() {
		return codServico;
	}

	public void setCodServico(String codServico) {
		this.codServico = codServico;
	}

	public String getLocalDestino() {
		return localDestino;
	}

	public void setLocalDestino(String localDestino) {
		this.localDestino = localDestino;
	}

	public String getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}

	public String getHoraFim() {
		return horaFim;
	}

	public void setHoraFim(String horaFim) {
		this.horaFim = horaFim;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}

	public String getUnidade() {
		return unidade;
	}

	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}

	public String getImp() {
		return imp;
	}

	public void setImp(String imp) {
		this.imp = imp;
	}
}  