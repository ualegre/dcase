/*
 * Copyright 2015 @author Unai Alegre 
 * 
 * This file is part of DCASE (Design for Context-Aware Systems Engineering), a module 
 * of Modelio that aids the design of a Context-Aware System (C-AS). 
 * 
 * DCASE is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * DCASE is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with DCASE. If not, see <http://www.gnu.org/licenses/>.
 * 
 */
package edu.casetools.dcase.modelio.menu;

import java.util.List;

import org.modelio.api.modelio.diagram.IDiagramHandle;
import org.modelio.api.modelio.diagram.IDiagramService;
import org.modelio.api.modelio.diagram.dg.IDiagramDG;
import org.modelio.api.modelio.diagram.style.IStyleHandle;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.ITransaction;
import org.modelio.api.module.IModule;
import org.modelio.api.module.command.DefaultModuleCommandHandler;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.diagrams.BehaviorDiagram;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.vcore.smkernel.mapi.MObject;

import edu.casetools.dcase.module.i18n.I18nMessageService;
import edu.casetools.dcase.module.impl.DCaseModule;

/**
 * The Class CreateDiagram has the common methods to create a diagram.
 */
public abstract class CreateBehaviourDiagram extends DefaultModuleCommandHandler {

    /*
     * (non-Javadoc)
     * 
     * @see org.modelio.api.module.commands.DefaultModuleCommandHandler#
     * actionPerformed (java.util.List, org.modelio.api.module.IModule)
     */
    @Override
    public void actionPerformed(List<MObject> selectedElements, IModule module) {
	IModelingSession session = DCaseModule.getInstance().getModuleContext().getModelingSession();
	ITransaction transaction = session
		.createTransaction(I18nMessageService.getString("Info.Session.Create", new String[] { "Activity" }));

	BehaviorDiagram diagram = (BehaviorDiagram) createOwnDiagram(selectedElements, session);

	if (null != diagram)
	    DCaseModule.getInstance().getModuleContext().getModelioServices().getEditionService().openEditor(diagram);
	transaction.commit();

    }

    /**
     * Creates the customized diagram.
     *
     * @param selectedElements
     *            the selected elements
     * @param session
     *            the session
     * @return the diagram to be created
     */
    protected abstract AbstractDiagram createOwnDiagram(List<MObject> selectedElements, IModelingSession session);

    /**
     * Common method to accept the diagram creation.
     *
     * @param selectedElements
     *            the selected elements
     * @param module
     *            the module
     * @return true, if it is accepted
     */
    /*
     * The IModule variable if forced by DefaultModuleCommandHandler
     */
    public boolean commonCheck(List<MObject> selectedElements, IModule module) { // NOSONAR

	if ((null != selectedElements) && (1 == selectedElements.size())
		&& (selectedElements.get(0) instanceof ModelElement))
	    return true;
	return false;
    }

    /**
     * Adds a style to the diagram.
     *
     * @param diagram
     *            the diagram where the style is going to be added.
     * @param styleName
     *            the name of the style.
     * @return the diagram where the style has been changed.
     */
    protected AbstractDiagram addStyle(AbstractDiagram diagram, String styleName) {
	IDiagramService ds = DCaseModule.getInstance().getModuleContext().getModelioServices().getDiagramService();
	IDiagramHandle handler = ds.getDiagramHandle(diagram);
	IDiagramDG dg = handler.getDiagramNode();

	for (IStyleHandle style : ds.listStyles()) {
	    if (style.getName().equals(styleName)) {
		dg.setStyle(style);
		break;
	    }
	}

	handler.save();
	handler.close();
	return diagram;
    }

}
