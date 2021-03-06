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
package edu.casetools.dcase.modelio.diagrams;

import java.util.List;

import org.eclipse.draw2d.geometry.Rectangle;
import org.modelio.api.modelio.Modelio;
import org.modelio.api.modelio.diagram.IDiagramGraphic;
import org.modelio.api.modelio.diagram.IDiagramHandle;
import org.modelio.api.modelio.diagram.IDiagramNode;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.ITransaction;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Note;
import org.modelio.vcore.smkernel.mapi.MObject;

import edu.casetools.dcase.module.api.DCaseNotes;
import edu.casetools.dcase.module.impl.DCaseModule;
import edu.casetools.dcase.module.impl.DCasePeerModule;
import edu.casetools.rcase.modelio.diagrams.ElementTool;
import edu.casetools.rcase.utils.ElementUtils;


/**
 * The Class RelationTool has the common methods to create the tool of a
 * relation.
 */
@SuppressWarnings("deprecation")
public abstract class ElementNoteTool extends ElementTool {
    /*
     * (non-Javadoc)
     * 
     * @see
     * org.modelio.api.diagram.tools.DefaultLinkTool#actionPerformed(org.modelio
     * .api.diagram.IDiagramHandle, org.modelio.api.diagram.IDiagramGraphic,
     * org.modelio.api.diagram.IDiagramGraphic,
     * org.modelio.api.diagram.IDiagramLink.LinkRouterKind,
     * org.modelio.api.diagram.ILinkPath)
     */
	
	protected String noteName;
	
	@Override
    public ITransaction createElement(IDiagramHandle representation, MObject element, IDiagramGraphic target, // NOSONAR
    	    Rectangle rect) {

    	IModelingSession session = Modelio.getInstance().getModelingSession();

    	ITransaction transaction = session.createTransaction("Create");

    	MObject createdElement = createOwnElement(session, element);
    	
		try {
			Note note = ElementUtils.getInstance().createNote(DCaseModule.getInstance().getModuleContext().getModelingSession().getModel(), DCasePeerModule.MODULE_NAME, (ModelElement) createdElement, DCaseNotes.NOTE_FEEDS_IN_WINDOW);
			note.setContent("No Ontological Properties");
			
		    representation.unmask(note, rect.x, rect.y+ 400);
		} catch (ExtensionNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


    	List<IDiagramGraphic> graph = representation.unmask(createdElement, rect.x, rect.y);

    	graph = representationConfigs(graph);

    	if ((null != graph) && (!graph.isEmpty()) && (graph.get(0) instanceof IDiagramNode)) {
    	    ((IDiagramNode) graph.get(0)).setBounds(rect);
    	}

    	representation.save();
    	representation.close();
    	transaction.commit();

    	return transaction;
        }

    protected void setNoteStereotype(String stereotype){
    	 this.noteName = stereotype;
    }

}