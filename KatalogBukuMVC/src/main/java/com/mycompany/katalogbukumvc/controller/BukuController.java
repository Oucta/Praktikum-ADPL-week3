/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.katalogbukumvc.controller;

import com.mycompany.katalogbukumvc.model.Buku;
import com.mycompany.katalogbukumvc.model.bukuModel;
import com.mycompany.katalogbukumvc.view.BukuView;



/**
 *
 * @MSI Gaming
 */
public class BukuController {
    private final bukuModel model;
    private final BukuView view;
    
    public BukuController(bukuModel model, BukuView view) {
        this.model = model;
        this.view = view;
        
        view.addSimpanListener(event -> simpanBuku());
        view.addHapusListener(event -> hapusBuku());
        view.addClearListener(event -> clearBuku());
        
        view.tampilkanData(model.getSemuaBuku());
    }
    
    public void simpanBuku(){
        String judul = view.getJudul().trim();
        String penulis = view.getPenulis().trim();
        String teksTahun = view.getTahunTerbit().trim();
        
        if(judul.isEmpty() || penulis.isEmpty() || teksTahun.isEmpty()){
            view.tampilkanPeringatan(
                    "Input belum lengkap",
                    "Judu;, Penulis, Tahun Terbit Wajib diisi."
            );
            return;
        }
        
        int tahunTerbit;
        try {
            tahunTerbit = Integer.parseInt(teksTahun);
        } catch (Exception e) {
            view.tampilkanPeringatan(
                    "input tidak valid",
                    "Tahun Terbit harus berupa angka"
            );
            return;
        }
        
        model.tambahBuku(new Buku(judul, penulis, tahunTerbit));
        view.tampilkanData(model.getSemuaBuku());
        view.kosongkanData();
    }
    
    public void hapusBuku(){
        int baris = view.getBarisTerpilih();
        
        if (baris == -1){
            view.tampilkanInfo(
                    "Hapus Buku",
                    "Pilih dulu baris yang akan dihapus."
            );
            return;
        }
        
        model.hapusBuku(baris);
        view.tampilkanData(model.getSemuaBuku());
        
    }
    
    public void clearBuku(){
        model.hapusSemua();
        view.tampilkanData(model.getSemuaBuku());
        view.kosongkanForm();
    }
    
}
