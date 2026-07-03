package com.aytug.ignite;

import java.util.Random;

import org.apache.ignite.Ignition;
import org.apache.ignite.client.ClientCache;
import org.apache.ignite.client.IgniteClient;
import org.apache.ignite.configuration.ClientConfiguration;

public class IgniteApp {
    public static void main(String[] args) {
        ClientConfiguration cfg = new ClientConfiguration().setAddresses("127.0.0.1:10800");

        System.out.println("⏳ Apache Ignite Cluster'a bağlanılıyor...");
        
        try (IgniteClient client = Ignition.startClient(cfg)) {
            System.out.println("✅ Ignite Cluster'a Thin Client ile başarıyla bağlanıldı!");

            ClientCache<Integer, Subscriber> cache = client.getOrCreateCache("Subscriber");

            cache.clear();
            System.out.println("🧹 Eski veriler temizlendi.");

            for (int i = 1; i <= 5; i++) {
                Subscriber initialSub = new Subscriber(i, 0.0, 0, 0);
                cache.put(i, initialSub);
            }
            System.out.println("📥 5 adet başlangıç abonesi başarıyla yüklendi.");

            Random rand = new Random();
            System.out.println("⏳ Kullanım simülasyonu başlatılıyor...");
            
            for (int i = 1; i <= 5; i++) {
                Subscriber sub = cache.get(i);
                if (sub != null) {
                    double newFreeData = Math.round((rand.nextDouble() * 5.0) * 100.0) / 100.0;
                    int newSms = rand.nextInt(50);
                    int newCalls = rand.nextInt(120);

                    sub.setDataUsage(sub.getDataUsage() + newFreeData);
                    sub.setSmsUsage(sub.getSmsUsage() + newSms);
                    sub.setCallUsage(sub.getCallUsage() + newCalls);

                    cache.put(i, sub);
                }
            }

            System.out.println("\n📊 TELEKOMÜNİKASYON GÜNCEL ABONE DURUMLARI:");
            System.out.println("--------------------------------------------------");
            for (int i = 1; i <= 5; i++) {
                System.out.println(cache.get(i));
            }
            System.out.println("--------------------------------------------------");

        } catch (Exception e) {
            System.err.println("❌ Apache Ignite üzerinde bir hata oluştu: " + e.getMessage());
        }
    }
}