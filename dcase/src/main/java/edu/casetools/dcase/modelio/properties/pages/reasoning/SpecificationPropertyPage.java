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
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Modelio. If not, see <http://www.gnu.org/licenses/>.
 * 
 */
package edu.casetools.dcase.modelio.properties.pages.reasoning;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.core.runtime.AssertionFailedException;
import org.modelio.api.module.propertiesPage.IModulePropertyTable;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.vcore.smkernel.mapi.MObject;

import edu.casetools.dcase.module.api.DCaseProperties;
import edu.casetools.dcase.module.api.DCaseStereotypes;
import edu.casetools.dcase.module.i18n.I18nMessageService;
import edu.casetools.dcase.module.impl.DCasePeerModule;
import edu.casetools.rcase.modelio.properties.IPropertyContent;
import edu.casetools.rcase.utils.PropertiesUtils;

public class SpecificationPropertyPage implements IPropertyContent {

    private static final Logger LOGGER = Logger.getLogger(SpecificationPropertyPage.class.getName());

    // TODO Reduce the complexity of the switch case
    @Override
    public void changeProperty(ModelElement element, int row, String value) {
	try {
	    switch (row) {
	    case 1:
		element.putTagValue(DCasePeerModule.MODULE_NAME, DCaseProperties.PROPERTY_SPECIFICATION_TYPE, value);
		break;
	    case 2:
		element.putTagValue(DCasePeerModule.MODULE_NAME, DCaseProperties.PROPERTY_SPECIFICATION, value);
		break;
	    default:
		break;
	    }
	} catch (ExtensionNotFoundException | AssertionFailedException e) {
	    LOGGER.log(Level.SEVERE, e.getMessage(), e);
	}

    }

    @Override
    public void update(ModelElement element, IModulePropertyTable table) {
	String property;

		// TagSpecificationType
		property = element.getTagValue(DCasePeerModule.MODULE_NAME, DCaseProperties.PROPERTY_SPECIFICATION_TYPE);
		table.addProperty(I18nMessageService.getString("Ui.Specification.Property.TagSpecificationType"), property,
			new String[] { I18nMessageService.getString("Ui.Specification.Property.TagSpecificationType.None"),
				I18nMessageService.getString("Ui.Specification.Property.TagSpecificationType.CTL"),
				I18nMessageService.getString("Ui.Specification.Property.TagSpecificationType.LTL"),
				I18nMessageService.getString("Ui.Specification.Property.TagSpecificationType.PSL"),
				I18nMessageService.getString("Ui.Specification.Property.TagSpecificationType.Invariant"),
				I18nMessageService.getString("Ui.Specification.Property.TagSpecificationType.Compute") });
	
		// TagSpecification
		property = PropertiesUtils.getInstance().getTaggedValue(DCaseProperties.PROPERTY_SPECIFICATION, element);
		table.addProperty(I18nMessageService.getString("Ui.Specification.Property.TagSpecification"), property);
	
		// TagPlatformValue
		updateSpecificationPlatform((ModelElement) element);
		property = element.getTagValue(DCasePeerModule.MODULE_NAME, DCaseProperties.PROPERTY_SPECIFICATION_PLATFORM);
		table.addConsultProperty(I18nMessageService.getString("Ui.Platform"), property);

	    }
	    
	    public static void updateSpecificationPlatform(ModelElement element){
	    	ContextStatePropertyPage.putTagValue(element, DCaseProperties.PROPERTY_SPECIFICATION_PLATFORM, getSpecificationPlatformType(element));
	    }

		public static String getSpecificationPlatformType(ModelElement element) {
			for(MObject child : element.getCompositionChildren()){
				if(((ModelElement)child).isStereotyped("SysMLArchitect", "Allocate")){
					if((((Dependency)child).getDependsOn()).isStereotyped(DCasePeerModule.MODULE_NAME, DCaseStereotypes.STEREOTYPE_ANDROID_REASONER)){
						return I18nMessageService.getString("Ui.Platform.Mobile");
					} else if((((Dependency)child).getDependsOn()).isStereotyped(DCasePeerModule.MODULE_NAME, DCaseStereotypes.STEREOTYPE_M_REASONER)){
						return I18nMessageService.getString("Ui.Platform.Stationary");
					}
				}
			}
			
			return I18nMessageService.getString("Ui.Platform.Unknown");
		}

}
