package com.api.bcb.currency.exchange.dollar.quote.day;

import java.io.IOException;
import java.security.InvalidParameterException;

import com.api.bcb.currency.commom.api.EntityApiInterface;
import com.api.bcb.currency.commom.api.date.simple.ValidateSimpleDate;
import com.api.bcb.currency.exchange.dollar.quote.day.ds.SearchResultDolarQuoteDayInterface;

/**
 * 1 - método public
 * 1 - modificador de fluxo
 * 2 - centena de linha na classe
 * 2 - meia centena de linha na método
 * 
 * Classe criada para acessar a quotação do dolar de um unico dia
 * no Endpoint /CotacaoDolarDia(dataCotacao=@dataCotacao)
 * 
 * Até o momento o único parâmetro obrigatório para acessar esse 
 * endpoint é a data da cotação
 */
public class DollarQuoteDay extends EntityApiInterface 
    implements DollarQuoteDayInterface {

    private final String date;

    public DollarQuoteDay(String date) {
        this.validateData(date);
        this.date = date;
    }

    //1
    @Override
    public SearchResultDolarQuoteDayInterface get() 
            throws IOException, InterruptedException {
        return new RequesterToDollarQuoteDayApi(this.getRequestParams())
            .doRequest();
    }

    @Override
    protected String getRequestParams() {
        return new StringBuilder("?@dataCotacao=")
            .append("'")
            .append(this.date)
            .append("'")
            .append("&format=json")
            .toString();
    }

    private void validateData(String date) {
        //1
        if(!ValidateSimpleDate.isValid(date)) {
            throw new InvalidParameterException("Data informada nao e valida");
        }
    }
    
}
