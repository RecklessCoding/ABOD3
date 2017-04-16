package com.recklesscoding.abode.debugger.prerecorded.instinct;

import com.recklesscoding.abode.core.plan.Plan;
import com.recklesscoding.abode.core.plan.planelements.PlanElement;
import com.recklesscoding.abode.core.plan.planelements.action.ActionEvent;
import com.recklesscoding.abode.debugger.prerecorded.LogReader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Athor: Andreas
 * Date: 30/01/2016.
 */
public class InstictLogReader extends LogReader {

    Map<Double, PlanElement> timePlanElementMap = new LinkedHashMap<>();

    @Override
    public void readFile(String file) {
        BufferedReader bufferedReader = null;
        String line;
        String cvsSplitBy = " ";

        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            double time;
            String typeOfPlanElement;
            String planElementName;
            PlanElement planElement = null;
            while ((line = bufferedReader.readLine()) != null) {
                String[] splittedLine = line.split(cvsSplitBy);

                // Making sure the search is not pointless
                if (isValidLine(splittedLine)) {
                    time = Double.parseDouble(splittedLine[0]);
                    typeOfPlanElement = splittedLine[2];
                    planElementName = splittedLine[3];
                    if (!isActionPatternElement(typeOfPlanElement)) { //We ignore ActionPatternELements as they are instinct only
                        planElement = getPlanElement(typeOfPlanElement, planElement, planElementName);
                        if (planElement != null) {
                            timePlanElementMap.put(time, planElement);
                        }
                        planElement = null;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public Map<Double, PlanElement> getTimePlanElementMap() {
        return timePlanElementMap;
    }

    private PlanElement getPlanElement(String typeOfPlanElement, PlanElement planElement, String planElementName) {
        if (isAction(typeOfPlanElement)) {
            planElement = Plan.getInstance().findAction(planElementName);
            if (planElement == null) {
                planElement = new ActionEvent(planElementName);
            }
        } else if (isActionPattern(typeOfPlanElement)) {
            planElement = Plan.getInstance().findActionPattern(planElementName);
            if (planElement == null) {
                planElement = new ActionEvent(planElementName);
            }
        } else if (isCompetence(typeOfPlanElement)) {
            planElement = Plan.getInstance().findCompetence(planElementName);
        } else if (isCompetenceElement(typeOfPlanElement)) {
            planElement = Plan.getInstance().findCompetenceElement(planElementName);
        } else if (isDrive(typeOfPlanElement)) {
            planElement = Plan.getInstance().findDriveCollection(planElementName);
        }
        return planElement;
    }

    private boolean isValidLine(String[] splittedLine) {
        return !splittedLine[0].startsWith("*") && splittedLine.length >= 4;
    }

    private boolean isActionPatternElement(String typeOfPlanElement) {
        return typeOfPlanElement.startsWith("APE");
    }

    private boolean isActionPattern(String typeOfPlanElement) {
        return typeOfPlanElement.equals("AP");
    }

    private boolean isAction(String typeOfPlanElement) {
        return typeOfPlanElement.equals("A");
    }

    private boolean isCompetence(String typeOfPlanElement) {
        return typeOfPlanElement.equals("C");
    }

    private boolean isCompetenceElement(String typeOfPlanElement) {
        return typeOfPlanElement.equals("CE");
    }

    private boolean isDrive(String typeOfPlanElement) {
        return typeOfPlanElement.equals("D");
    }
}
