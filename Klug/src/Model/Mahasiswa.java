/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;

/**
 *
 * @author faiz
 */
public class Mahasiswa extends Orang{
    private String nim;
    private String prodi;
    private ArrayList<Kelas> kelas;
    private ArrayList<Nilai> nilai;
    private ArrayList<Kehadiran> kehadiran;
    private ArrayList<JawabanMahasiswa> jawaban;
    private ArrayList<TugasMhs> tugas;

    public Mahasiswa(String username, String password, String nama, String nim, String prodi) {
        super(username,password,nama);
        this.nim = nim;
        this.prodi = prodi;
        nilai = new ArrayList<>();
        kehadiran = new ArrayList<>();
        jawaban = new ArrayList<>();
        kelas = new ArrayList<>();
        tugas = new ArrayList<>();
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getProdi() {
        return prodi;
    }

    public void setProdi(String prodi) {
        this.prodi = prodi;
    }

    public ArrayList<Nilai> getNilaiList() {
        return nilai;
    }

    public ArrayList<Kehadiran> getKehadiranList() {
        return kehadiran;
    }

    public ArrayList<JawabanMahasiswa> getJawabanList() {
        return jawaban;
    }
    
    public ArrayList<Kelas> getKelasList() {
        return kelas;
    }
    
    public ArrayList<TugasMhs> getTugasList() {
        return tugas;
    }
    
    public Nilai getNilai(int i) {
        return nilai.get(i);
    }

    public Kehadiran getKehadiran(int i) {
        return kehadiran.get(i);
    }

    public JawabanMahasiswa getJawaban(int i) {
        return jawaban.get(i);
    }
    
    public Kelas getKelas(int i) {
        return kelas.get(i);
    }
    
    public TugasMhs getTugas(int i) {
        return tugas.get(i);
    }
    
    public void addKelas(Kelas kelas){
        this.kelas.add(kelas);
    }
    
    public void createTugas(TugasMhs tugas){
        this.tugas.add(tugas);
    }
    
    public void createNilai(Nilai nilai){
        this.nilai.add(nilai);
    }
    
    public void createJawaban(JawabanMahasiswa jawaban){
        this.jawaban.add(jawaban);
    }
    
    public void createKehadiran(Kehadiran kehadiran){
        this.kehadiran.add(kehadiran);
    }
    
    public int getTotalPertemuan(Kelas kelas){
        int total = 0;
        for (int i=0;i<kehadiran.size();i++){
            if (kehadiran.get(i).getKehadiran().getKelas() == kelas){
                total = kehadiran.get(i).getKehadiran().getKelas().getKehadiran().size();
            }
        }
        return total;
    }
    
    public int getTotalKehadiran(Kelas kelas){
        int total = 0;
        for (int i=0;i<kehadiran.size();i++){
            if (kehadiran.get(i).getKehadiran().getKelas() == kelas){
                if (kehadiran.get(i).isHadir()){
                    total++;
                }
            }
        }
        return total;
    }
       
    public double getPersentaseKehadiran(Kelas kelas){
        double total = getTotalKehadiran(kelas);
        double pertemuan = getTotalPertemuan(kelas);
        double persen = (total/pertemuan)*100;
        return persen;
    }
}
