package org.sid.web;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.sid.dto.FactureDto;
import org.sid.services.FactureService;
import org.sid.utils.PDFGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pdf")
public class CustomerRestAPIs {
	
    
    @Autowired
    FactureService factureService;

//    @GetMapping(value = "/customers",
//            produces = MediaType.APPLICATION_PDF_VALUE)
//    public ResponseEntity<InputStreamResource> customerReport() throws IOException {
//        List<Customer> customers = (List<Customer>) customerRepository.findAll();
//
//        ByteArrayInputStream bis = PDFGenerator.customerPDFReport(customers);
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Content-Disposition", "inline; filename=customers.pdf");
//
//        return ResponseEntity
//                .ok()
//                .headers(headers)
//                .contentType(MediaType.APPLICATION_PDF)
//                .body(new InputStreamResource(bis));
//    }
    
    @GetMapping(value = "/facture/{id}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> factureById(@PathVariable("id") Long id) throws IOException {
    	
    	FactureDto factureDto = factureService.findFacturById(id);

        ByteArrayInputStream bis = PDFGenerator.customerPDFReport(factureDto);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=customers.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }
}