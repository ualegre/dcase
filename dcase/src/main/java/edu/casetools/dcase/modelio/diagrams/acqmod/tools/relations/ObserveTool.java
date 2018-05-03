/*
 * Copyright 2015 @author Unai Alegre 
 * 
 * This file is part of R-CASE (Requirements for Context-Aware Systems Engineering), a module 
 * of Modelio that aids the requirements elicitation phase of a Context-Aware System (C-AS). 
 * 
 * R-CASE is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * R-CASE is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Modelio.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */
package edu.casetools.dcase.modelio.diagrams.acqmod.tools.relations;

import org.modelio.api.modelio.diagram.IDiagramGraphic;
import org.modelio.api.modelio.diagram.IDiagramHandle;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.ModelElement;

import edu.casetools.dcase.module.api.DCaseStereotypes;
import edu.casetools.dcase.module.impl.DCaseModule;
import edu.casetools.dcase.module.impl.DCasePeerModule;
import edu.casetools.rcase.modelio.diagrams.RelationTool;
import edu.casetools.rcase.module.api.RCaseStereotypes;
import edu.casetools.rcase.module.impl.RCasePeerModule;
import edu.casetools.rcase.utils.ElementUtils;

/**
 * The Class CopyTool is the tool for creating a Copy relation.
 */
public class ObserveTool extends RelationTool {

	
	private boolean isPreferenceSensor;
	private boolean isMobileOrStationarySensor;
    /*
     * (non-Javadoc)
     * 
     * @see
     * org.modelio.api.diagram.tools.DefaultLinkTool#acceptFirstElement(org.
     * modelio.api.diagram.IDiagramHandle,
     * org.modelio.api.diagram.IDiagramGraphic)
     */
    @Override
    public boolean acceptFirstElement(IDiagramHandle representation, IDiagramGraphic target) {
    	isPreferenceSensor =  acceptElement(DCasePeerModule.MODULE_NAME, target, DCaseStereotypes.STEREOTYPE_PREFERENCE_SENSOR);
    	isMobileOrStationarySensor = acceptElement(DCasePeerModule.MODULE_NAME, target, DCaseStereotypes.STEREOTYPE_MOBILE_SENSOR) ||
    							 acceptElement(DCasePeerModule.MODULE_NAME, target, DCaseStereotypes.STEREOTYPE_STATIONARY_SENSOR);
    	return isPreferenceSensor || isMobileOrStationarySensor;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.modelio.api.diagram.tools.DefaultLinkTool#acceptSecondElement(org
     * .modelio.api.diagram.IDiagramHandle,
     * org.modelio.api.diagram.IDiagramGraphic,
     * org.modelio.api.diagram.IDiagramGraphic)
     */
    @Override
    public boolean acceptSecondElement(IDiagramHandle representation, IDiagramGraphic source, IDiagramGraphic target) {
    	if(isPreferenceSensor)
    		return acceptElement(RCasePeerModule.MODULE_NAME, target, RCaseStereotypes.STEREOTYPE_CONTEXT_PREFERENCE);
    	if(isMobileOrStationarySensor)
    		return acceptElement(RCasePeerModule.MODULE_NAME, target, RCaseStereotypes.STEREOTYPE_CONTEXT_ATTRIBUTE);
    	return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see edu.casesuite.modelio.diagrams.RelationTool#
     * createDependency(org.modelio.metamodel.uml.infrastructure.ModelElement,
     * org.modelio.metamodel.uml.infrastructure.ModelElement)
     */
    @Override
    public Dependency createDependency(ModelElement originElement, ModelElement targetElement) {
	return ElementUtils.getInstance().createDependency(DCaseModule.getInstance(), DCasePeerModule.MODULE_NAME, originElement, targetElement,
		DCaseStereotypes.STEREOTYPE_OBSERVE);
    }

}
