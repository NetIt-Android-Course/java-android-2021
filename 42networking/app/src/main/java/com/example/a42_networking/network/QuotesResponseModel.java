package com.example.a42_networking.network;

public class QuotesResponseModel {

    int statusCode;
    String message;
    PaginationModel pagination;
    Integer totalQuotes;
    public QuoteModel[] data;
}
