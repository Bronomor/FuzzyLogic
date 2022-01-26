package sample;

import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.rule.FuzzyRuleSet;

public class Fuzzy {

    private FuzzyRuleSet fuzzyRuleSet;
    private String configuration = "input.fcl";

    public Fuzzy(){
        String fileName = configuration;
        FIS fis = FIS.load(fileName,false);
        fuzzyRuleSet = fis.getFuzzyRuleSet();
    }

    public int getDefuzzy(int odlegloscXDoDziury, int odlegloscYDoDziury, int odlegloscOdLewegoBoku){

        fuzzyRuleSet.setVariable("odlegloscXDoDziury", odlegloscXDoDziury);
        fuzzyRuleSet.setVariable("odlegloscYDoDziury", odlegloscYDoDziury);
        fuzzyRuleSet.setVariable("odlegloscOdLewegoBoku", odlegloscOdLewegoBoku);

        fuzzyRuleSet.evaluate();

        //System.out.println(fuzzyRuleSet.getVariable("skretSamochodu"));
        //System.out.println(fuzzyRuleSet.getVariable("skretSamochodu").getLatestDefuzzifiedValue());

        return (int) fuzzyRuleSet.getVariable("skretSamochodu").getLatestDefuzzifiedValue();
    }

}