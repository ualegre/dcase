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
package edu.casetools.dcase.modelio.properties.pages.deployment;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.core.runtime.AssertionFailedException;
import org.modelio.api.module.propertiesPage.IModulePropertyTable;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.infrastructure.ModelElement;

import edu.casetools.dcase.module.api.DCaseProperties;
import edu.casetools.dcase.module.i18n.I18nMessageService;
import edu.casetools.dcase.module.impl.DCasePeerModule;
import edu.casetools.rcase.modelio.properties.IPropertyContent;
import edu.casetools.rcase.utils.PropertiesUtils;

public class VeraActuatorPropertyPage implements IPropertyContent {

    private static final Logger LOGGER = Logger.getLogger(VeraActuatorPropertyPage.class.getName());

    // TODO Reduce the complexity of the switch case
    @Override
    public void changeProperty(ModelElement element, int row, String value) {
	try {
	    switch (row-1) {
		    case 1:
			element.putTagValue(DCasePeerModule.MODULE_NAME, DCaseProperties.PROPERTY_VERA_ACTUATOR_IP, value);
			break;
		    case 2:
			element.putTagValue(DCasePeerModule.MODULE_NAME, DCaseProperties.PROPERTY_VERA_ACTUATOR_PORT, value);
			break;
		    case 3:
			element.putTagValue(DCasePeerModule.MODULE_NAME, DCaseProperties.PROPERTY_VERA_ACTUATOR_SERVICE_ID, value);
			break;
		    case 4:
			element.putTagValue(DCasePeerModule.MODULE_NAME, DCaseProperties.PROPERTY_VERA_ACTUATOR_ACTION_COMMAND, value);
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

	
		property = PropertiesUtils.getInstance().getTaggedValue(DCaseProperties.PROPERTY_VERA_ACTUATOR_IP, element);
		table.addProperty(I18nMessageService.getString("Ui.VeraActuator.Property.IP"), property);
		
		property = PropertiesUtils.getInstance().getTaggedValue(DCaseProperties.PROPERTY_VERA_ACTUATOR_PORT, element);
		table.addProperty(I18nMessageService.getString("Ui.VeraActuator.Property.Port"), property);
		
		property = PropertiesUtils.getInstance().getTaggedValue(DCaseProperties.PROPERTY_VERA_ACTUATOR_SERVICE_ID, element);
		table.addProperty(I18nMessageService.getString("Ui.VeraActuator.Property.ServiceId"), property);
		
		property = PropertiesUtils.getInstance().getTaggedValue(DCaseProperties.PROPERTY_VERA_ACTUATOR_ACTION_COMMAND, element);
		table.addProperty(I18nMessageService.getString("Ui.VeraActuator.Property.ActionCommand"), property);

    }
    

}
