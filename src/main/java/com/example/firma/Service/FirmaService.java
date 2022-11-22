package com.example.firma.Service;

import com.example.firma.DTO.BolimDTO;
import com.example.firma.DTO.FirmaDTO;
import com.example.firma.DTO.IshchiDTO;
import com.example.firma.Entity.Bolim;
import com.example.firma.Entity.Firma;
import com.example.firma.Entity.Ishchi;
import com.example.firma.Entity.Manzil;
import com.example.firma.Payload.APIResponse;
import com.example.firma.Repository.BolimRepository;
import com.example.firma.Repository.FirmaRepository;
import com.example.firma.Repository.IshchiRepository;
import com.example.firma.Repository.ManzilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FirmaService {
    @Autowired
    FirmaRepository firmaRepository;
    @Autowired
    ManzilRepository manzilRepository;
    @Autowired
    BolimRepository bolimRepository;
    @Autowired
    IshchiRepository ishchiRepository;
    public APIResponse add(FirmaDTO firmaDTO) {
        Optional<Firma> byFirmaNomi = firmaRepository.findByFirmaNomi(firmaDTO.getFirmaNomi());
        if(byFirmaNomi.isPresent()) {
            return new APIResponse("Bunday firma mavjud", false);
        }
        Firma firma=new Firma();
        firma.setFirmaNomi(firmaDTO.getFirmaNomi());
        firma.setDirektorNomi(firmaDTO.getDirektorNomi());
        Manzil manzil=new Manzil();
        manzil.setViloyat(firmaDTO.getViloyat());
        manzil.setTuman(firmaDTO.getTuman());
        manzil.setKocha(firmaDTO.getKocha());
        manzil.setUyRaqami(firmaDTO.getUyRaqami());
        manzilRepository.save(manzil);
        firma.setManzil(manzil);
        firmaRepository.save(firma);
        return new APIResponse("Firma muvaffaqiyatli qo'shildi",true);
    }

    public APIResponse readId(Integer id) {
        Optional<Firma> byId = firmaRepository.findById(id);
        if(byId.isPresent()){
           Firma firma=byId.get();
           Manzil manzil=manzilRepository.findById(firmaRepository.findById(id).get().getManzil().getId()).get();
           String xabar="Firma Nomi:"+firma.getFirmaNomi()+"\n"+"Firma Direktori: "+firma.getFirmaNomi()+"\n"+
                   "Viloyat: "+manzil.getViloyat()+"\n"+"Tuman:"+manzil.getTuman()+"\n"+"Ko'cha:" +manzil.getKocha()+"\n"+
                   "Uy raqami:"+manzil.getUyRaqami();
           return new APIResponse(xabar,true);
        }
        return new APIResponse("Bunday idli firma mavjud emas",false);
    }

    public APIResponse editId(Integer id, FirmaDTO firmaDTO) {
        Optional<Firma> byId = firmaRepository.findById(id);
        if(byId.isPresent()){
            Firma firma=byId.get();
            Manzil manzil=manzilRepository.findById(firma.getManzil().getId()).get();
            firma.setFirmaNomi(firmaDTO.getFirmaNomi());
            firma.setDirektorNomi(firmaDTO.getDirektorNomi());
            manzil.setViloyat(firmaDTO.getViloyat());
            manzil.setTuman(firmaDTO.getTuman());
            manzil.setKocha(firmaDTO.getKocha());
            manzil.setUyRaqami(firmaDTO.getUyRaqami());
            manzilRepository.save(manzil);
            firma.setManzil(manzil);
            firmaRepository.save(firma);
            return new APIResponse("Firma ma'lumotlari tahrirlandi",true);
        }
        return new APIResponse("Bunday idli firma mavjud emas",false);
    }

    public APIResponse deleteId(Integer id) {
        Optional<Firma> byId = firmaRepository.findById(id);
        if(byId.isPresent()){
            firmaRepository.deleteById(id);
            manzilRepository.deleteById(firmaRepository.findById(id).get().getManzil().getId());
            return new APIResponse("Muvaffaqiyatli o'chirildi",true);
        }
        return new APIResponse("Bunday idli firma topilmadi",false);
    }

    public APIResponse bolimAdd(BolimDTO bolimDTO) {
        Optional<Bolim> byNomi = bolimRepository.findByNomiAndFirmaId(bolimDTO.getNomi(),bolimDTO.getFirmaId());
        if(byNomi.isPresent()){
            return new APIResponse("Bunday bo'lim qo'shilgan",false);
        }
        Bolim bolim=new Bolim();
        bolim.setNomi(bolimDTO.getNomi());
        bolim.setFirma(firmaRepository.findById(bolimDTO.getFirmaId()).get());
        bolimRepository.save(bolim);
        return new APIResponse("Bolim firmaga qo'shildi",true);
    }


    public APIResponse bolimtahrirlash(Integer id, BolimDTO bolimDTO) {
        Optional<Bolim> byIdAndFirmaId = bolimRepository.findByIdAndFirmaId(id, bolimDTO.getFirmaId());
        if(byIdAndFirmaId.isPresent()){
            Bolim bolim=byIdAndFirmaId.get();
            bolim.setNomi(bolimDTO.getNomi());
            bolim.setFirma(firmaRepository.findById(bolimDTO.getFirmaId()).get());
            bolimRepository.save(bolim);
            return new APIResponse("Bo'lim tahrirlandi",true);
        }
        return new APIResponse("Bunday idli bo'lim mavjud emas",false);
    }

    public APIResponse bolimochirish(Integer id) {
        Optional<Bolim> byId = bolimRepository.findById(id);
        if(byId.isPresent()){
            bolimRepository.deleteById(id);
            return new APIResponse("Bo'lim o'chirildi",true);
        }
        return new APIResponse("Bunday idli bo'lim mavjud emas",false);
    }

    public APIResponse bolimOqish(Integer id) {
        Optional<Bolim> byId = bolimRepository.findById(id);
        if(byId.isPresent()){
            String xabar=byId.get().toString();
            return new APIResponse(xabar,true);
        }
        return new APIResponse("Bunday bo'lim mavjud emas",false);
    }

    public APIResponse ishchiqoshish(IshchiDTO ishchiDTO) {
        Optional<Ishchi> byTelRaqam = ishchiRepository.findByTelRaqam(ishchiDTO.getTelRaqam());
        if(byTelRaqam.isPresent()){
            return new APIResponse("Bunday ishchi mavjud",false);
        }
        Optional<Bolim> byId = bolimRepository.findById(ishchiDTO.getBolimId());
        if(byId.isPresent()){
            Ishchi ishchi=new Ishchi();
            ishchi.setIsm(ishchiDTO.getIsm());
            ishchi.setFamiliya(ishchiDTO.getFamiliya());
            ishchi.setTelRaqam(ishchiDTO.getTelRaqam());
            Manzil manzil=new Manzil();
            manzil.setViloyat(ishchiDTO.getViloyat());
            manzil.setTuman(ishchiDTO.getTuman());
            manzil.setKocha(ishchiDTO.getKocha());
            manzil.setUyRaqami(ishchiDTO.getUyRaqami());
            manzilRepository.save(manzil);
            ishchi.setManzil(manzil);
            ishchi.setBolim(byId.get());
            ishchiRepository.save(ishchi);
            return new APIResponse("Ishchi qo'shildi",true);
        }
        return new APIResponse("Bunday idli bo'lim mavjud emas",false);

    }

    public APIResponse ishchioqish(Integer id) {
        Optional<Ishchi> byId = ishchiRepository.findById(id);
        if(byId.isPresent()){
            String xabar=byId.get().toString();
            return new APIResponse(xabar,true);
        }
        return new APIResponse("Bunday ishchi mavjud emas",false);
    }

    public APIResponse ishchitahrirlash(Integer id, IshchiDTO ishchiDTO) {
        Optional<Ishchi> byId = ishchiRepository.findById(id);
        if(byId.isPresent()){
            Ishchi ishchi=byId.get();
            Optional<Bolim> byId1 = bolimRepository.findById(ishchiDTO.getBolimId());
            if(byId1.isPresent()){
                ishchi.setIsm(ishchiDTO.getIsm());
                ishchi.setFamiliya(ishchiDTO.getFamiliya());
                Manzil manzil=byId.get().getManzil();
                manzil.setViloyat(ishchiDTO.getViloyat());
                manzil.setTuman(ishchiDTO.getTuman());
                manzil.setKocha(ishchiDTO.getKocha());
                manzil.setUyRaqami(ishchiDTO.getUyRaqami());
                manzilRepository.save(manzil);
                ishchi.setManzil(manzil);
                ishchi.setBolim(byId1.get());
                ishchiRepository.save(ishchi);
                return new APIResponse("Ishchi ma'lumotlari tahrirlandi",true);
            }
            return new APIResponse("Bunday bo'lim mavjud emas",false);
        }
        return new APIResponse("Bunday idli ishchi mavjud emas",false);
    }

    public APIResponse ishchi_ochirish(Integer id) {
        Optional<Ishchi> byId = ishchiRepository.findById(id);
        if(byId.isPresent()){
            ishchiRepository.deleteById(id);
            manzilRepository.deleteById(byId.get().getManzil().getId());
            return new APIResponse("Ishchi ma'lumotlari o'chirildi",true);
        }
        return new APIResponse("Bunday idli ishchi mavjud emas",false);
    }
}
