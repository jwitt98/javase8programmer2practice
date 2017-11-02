/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javase8programmer2practice;
import java.time.LocalDate;
/**
 *
 * @author jwitt98
 */
import java.time.format.DateTimeFormatter;
public class DateTest {
    
    public static void main(String[] args){
        LocalDate ld = LocalDate.parse("12/01/2017", DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        System.out.println(ld.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));//prints 01/12/2017
    }
    
}


