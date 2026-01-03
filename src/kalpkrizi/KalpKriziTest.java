package kalpkrizi;

import java.net.URISyntaxException;
import java.util.Scanner;

import kalpkrizi.KalpKriziProgram;
import net.sourceforge.jFuzzyLogic.plot.JFuzzyChart;

public class KalpKriziTest {

	public static void main(String[] args) throws URISyntaxException {
		try (Scanner in = new Scanner(System.in)) {
			System.out.print("Uyku Süresi: ");
			double uykusuresi = in.nextDouble();
			System.out.print("Yağ Tüketimi: ");
			double yagtuketimi = in.nextDouble();
			KalpKriziProgram kalpKriziOlasiligi = new KalpKriziProgram(uykusuresi, yagtuketimi);
			System.out.println(kalpKriziOlasiligi);
			JFuzzyChart.get().chart(kalpKriziOlasiligi.getModel());
			// 2. Sadece çalışan kuralları yazdır
	        System.out.println(kalpKriziOlasiligi.getCalisanKurallar());

	        // 3. Grafiği göster
	        JFuzzyChart.get().chart(kalpKriziOlasiligi.getModel());
	        
	        in.close();
		}
		
	}

}
