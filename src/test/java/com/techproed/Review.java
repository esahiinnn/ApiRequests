package com.techproed;

public class Review {
    // 1) Asertion icin kac farkli yontem vardır ?

    // Body- Matchers -- Hard Assert
    // JsonPath-- Nesne olusturduk, json uzerinden response a ulastık
    // GSON-- De-Serialization
    // POJO -- Class lar olusturdk--
    // Object Mapper

    // 2) ReqBody --- Patch,Post,Put  ExpectedData---- Get ve Delete
    // ReqBody ve ExpectedData yi hangi sekillerde olusturduk
    // Data Collections
    // JSONObject
    // ObjectMapper
    // POJO

    // 3) TestBase class larımız
    // RequestSpecification-- Interface in den nesne urettik
    // RequestBuilder methodunu kullandık
    // build() metodunu --
    // Before Annotation unun kullandım-- JUnit teki Test metodunu kullandık
    // Test ten once specleri olusturmak icin Before u kullandık.


    // spec.patParams ("","");-- URl i olusturduk
    // query params varsa (/todos?esefagkj=skfds)
    // spec.pathparams().queryparams();

    // Hard Assertion-- assertEquals,assertTrue,assertFalse
    // Hard Assertion-- body-- Matchers--

    // Soft Assert --- assertEquals,assertTrue,assertFalse

    // Neleri assert ettik
    // status code, content type, datayı -- expected data ve response u assert ettik.
}
