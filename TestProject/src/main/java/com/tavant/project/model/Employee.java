package com.tavant.project.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.transaction.TransactionScoped;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.ManyToAny;
import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.sym.Name;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor

@AllArgsConstructor
@Data
@Component
@Entity(name =  "Employee")
@Table(name = "employees")
public class Employee{
	
	
	@Id @Column(name = "employeeNumber")
    private Integer employeeId;
   
    private String firstName;
     
    private String lastName;
    
    @Transient
    private String extension;  
  
    @Email(message = "email should be valid")
    private String email;

    private Integer officeCode;
    
    private Integer reportsTo;
    
  
    private String jobTitle;
	
	
}
