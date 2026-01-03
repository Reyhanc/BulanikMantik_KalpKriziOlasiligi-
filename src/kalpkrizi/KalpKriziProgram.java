package kalpkrizi;

import java.io.File;
import java.net.URISyntaxException;
import net.sourceforge.jFuzzyLogic.rule.Rule;
import net.sourceforge.jFuzzyLogic.rule.RuleBlock;

import net.sourceforge.jFuzzyLogic.FIS;

public class KalpKriziProgram {
	private  FIS fis;
	private double uykusuresi;
	private double yagtuketimi;
	
	public KalpKriziProgram (double uykusuresi, double yagtuketimi) throws URISyntaxException {
		this.uykusuresi = uykusuresi;
		this.yagtuketimi = yagtuketimi;
		
		File dosya = new File(getClass().getResource("kalpkrizmodel.fcl").toURI());
	    fis = FIS.load(dosya.getPath());
	    fis.setVariable("uykusuresi", uykusuresi);
	    fis.setVariable("yagtuketimi", yagtuketimi);
	    fis.evaluate();
	}
	public FIS getModel() {
		return fis;
	}
	
	@Override
	public String toString() {
		String cikti;
		cikti = "Kalp Krizi Olasılığı: "+Math.round(fis.getVariable("kalpkriziolasiligi").getValue())+" %";
		return cikti;
	}
	
	public String getCalisanKurallar() {
        StringBuilder rapor = new StringBuilder();
        rapor.append("--- Çalışan Kurallar ---\n");

        String functionBlockAdi = "kalpKriziOlasiligiModel"; // FCL'deki ad

        for( RuleBlock ruleBlock : fis.getFunctionBlock(functionBlockAdi).getRuleBlocks().values() ) {
            for( Rule kural : ruleBlock.getRules() ) {
                
                // Eğer kuralın ateşlenme derecesi 0'dan büyükse, adını yazdır
                if (kural.getDegreeOfSupport() > 0) {
                    rapor.append(kural.getName() + "\n");
                }
            }
        }
        rapor.append("------------------------\n");
        return rapor.toString();
    }

}
