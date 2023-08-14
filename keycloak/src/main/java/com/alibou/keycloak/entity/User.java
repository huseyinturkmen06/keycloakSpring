package com.alibou.keycloak.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="customers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    //tc , telefon numarası, operator

    //ilk olarak kullanıcı tc si sistemde var mı diye kontrol edilir,
    //client_id  ---> TC
    //username   ---> telefon numarası
    //password  ---> operator

    //keycloak içindeki 4 parametreye erişemediğimiz ve özelleştiremediğimiz için
    //şimdilik bu şekilde yaptık
    //erişince değiştirilecek

    String grant_type = "password";
    //şimdilik bunu sabit yaptık, token endpointe giden 4 parametreden 1 tanesi;
    //diğerleri json olarak alınacak (Request Body)


    @Id
    @Column(name="tc_no")
    private String client_id;

    @Column(name="telefon_no")
    private String tel_no;

    @Column(name="operator")
    private String operator;




}
