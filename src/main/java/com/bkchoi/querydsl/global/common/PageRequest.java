package com.bkchoi.querydsl.global.common;

import org.springframework.data.domain.Sort;

import java.net.URLDecoder;
import java.util.Objects;

public class PageRequest {
    private int page = 1;
    private int size = 20;
    private String sort;

    public void setPage(int page) {
        this.page = page <= 0 ? 1 : page;
    }

    public void setSize(int size) {
        int DEFAULT_SIZE = 20;
        int MAX_SIZE = 50;
        this.size = size;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public org.springframework.data.domain.PageRequest of() {
        // sort nullable
        String sortStr = Objects.isNull(this.sort) || this.sort.isEmpty() ? "updatedDate,DESC" : this.sort;
        String[] sortQuery = sortStr.split(",");
        String properties;
        String sort ;
        //URL이 이중 인코딩 되는 경우때문에 서버에서 한번 더 체크한다.
        if(sortQuery.length<2){
            String decodeSortStr = null;
            try{
                decodeSortStr = URLDecoder.decode(sortStr, "UTF-8");
            }catch (Exception e){
                System.out.println(e);
            }
            String[] decodeSortQuery = decodeSortStr.split(",");

            properties = decodeSortQuery[0];
            sort = decodeSortQuery[1].toUpperCase();
        }else{
            properties = sortQuery[0];
            sort = sortQuery[1].toUpperCase();
        }



        if (!(sort.equals("ASC") || sort.equals("DESC"))) {
            sort = "DESC";
        }

        if (properties == null || properties.isEmpty()) {
            properties = "createdDate";
        }

        return org.springframework.data.domain.PageRequest.of(page - 1, size, Sort.Direction.valueOf(sort), properties);
    }
}
