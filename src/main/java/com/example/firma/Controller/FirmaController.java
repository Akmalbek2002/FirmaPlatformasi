package com.example.firma.Controller;

import com.example.firma.DTO.BolimDTO;
import com.example.firma.DTO.FirmaDTO;
import com.example.firma.DTO.IshchiDTO;
import com.example.firma.Payload.APIResponse;
import com.example.firma.Service.FirmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/firma")
public class FirmaController {
      @Autowired
    FirmaService firmaService;
      // Firma CRUDi
      @PostMapping("/firmajoylash")
    public HttpEntity<?> firmajoylash(@RequestBody FirmaDTO firmaDTO){
          APIResponse apiResponse=firmaService.add(firmaDTO);
          return ResponseEntity.status(apiResponse.isHolat() ? HttpStatus.OK :HttpStatus.ALREADY_REPORTED).body(apiResponse.getXabar());
      }
      @GetMapping("/firmaoqish/{id}")
    public HttpEntity<?> firma_oqish(@PathVariable Integer id){
          APIResponse apiResponse=firmaService.readId(id);
          return ResponseEntity.status(apiResponse.isHolat() ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(apiResponse.getXabar());
      }
      @PutMapping("/firmatahrirlash/{id}")
    public HttpEntity<?> firmatahrirlash(@PathVariable Integer id,@RequestBody FirmaDTO firmaDTO){
          APIResponse apiResponse=firmaService.editId(id,firmaDTO);
          return ResponseEntity.status(apiResponse.isHolat() ? HttpStatus.OK :HttpStatus.NOT_FOUND).body(apiResponse.getXabar());
      }
      @DeleteMapping("/firmaochirish/{id}")
    public HttpEntity<?> firma_ochirish(@PathVariable Integer id){
          APIResponse apiResponse=firmaService.deleteId(id);
          return ResponseEntity.status(apiResponse.isHolat() ? HttpStatus.OK:HttpStatus.NOT_FOUND).body(apiResponse.getXabar());
      }

      // Bo'lim CRUDi
    @PostMapping("/bolimqoshish")
    public HttpEntity<?> bolimQoshish(@RequestBody BolimDTO bolimDTO){
          APIResponse apiResponse=firmaService.bolimAdd(bolimDTO);
          return ResponseEntity.status(apiResponse.isHolat() ? HttpStatus.OK:HttpStatus.ALREADY_REPORTED).body(apiResponse.getXabar());
    }
    @GetMapping("/bolimoqish/{id}")
    public HttpEntity<?> bolimOqish(@PathVariable Integer id){
        APIResponse apiResponse=firmaService.bolimOqish(id);
        return ResponseEntity.status(apiResponse.isHolat() ? HttpStatus.OK:HttpStatus.NOT_FOUND).body(apiResponse.getXabar());
      }
      @PutMapping("/bolimtahrirlash/{id}")
    public HttpEntity<?> bolimtahrirlash(@PathVariable Integer id,@RequestBody BolimDTO bolimDTO){
          APIResponse apiResponse=firmaService.bolimtahrirlash(id,bolimDTO);
          return  ResponseEntity.status(apiResponse.isHolat() ? HttpStatus.OK:HttpStatus.NOT_FOUND).body(apiResponse.getXabar());
      }
      @DeleteMapping("/bolimochirish/{id}")
    public HttpEntity<?> bolimochirish(@PathVariable Integer id){
          APIResponse apiResponse=firmaService.bolimochirish(id);
          return ResponseEntity.status(apiResponse.isHolat() ? HttpStatus.OK:HttpStatus.NOT_FOUND).body(apiResponse.getXabar());
      }

      // Ishchi CRUDi
    @PostMapping("/ishchiqoshish")
    public HttpEntity<?> ishchiqoshish(@RequestBody IshchiDTO ishchiDTO){
          APIResponse apiResponse=firmaService.ishchiqoshish(ishchiDTO);
          return ResponseEntity.status(apiResponse.isHolat()?HttpStatus.OK:HttpStatus.ALREADY_REPORTED).body(apiResponse.getXabar());
    }
    @GetMapping("/ishoqish/{id}")
    public HttpEntity<?> ishchioqish(@PathVariable Integer id){
          APIResponse apiResponse=firmaService.ishchioqish(id);
          return ResponseEntity.status(apiResponse.isHolat()?HttpStatus.OK:HttpStatus.NOT_FOUND).body(apiResponse.getXabar());
    }
    @PutMapping("/ishchitahrirlash/{id}")
    public HttpEntity<?> ishchitahrirlash(@PathVariable Integer id,@RequestBody IshchiDTO ishchiDTO){
          APIResponse apiResponse=firmaService.ishchitahrirlash(id,ishchiDTO);
          return ResponseEntity.status(apiResponse.isHolat() ? HttpStatus.OK:HttpStatus.NOT_FOUND).body(apiResponse.getXabar());
    }
    @DeleteMapping("/ishchiochirish/{id}")
    public HttpEntity<?> ishchi_ochirish(@PathVariable Integer id){
          APIResponse apiResponse=firmaService.ishchi_ochirish(id);
          return ResponseEntity.status(apiResponse.isHolat()?HttpStatus.OK:HttpStatus.NOT_FOUND).body(apiResponse.getXabar());
    }

}
