D-CASE: Design for Context-Aware Systems Engineering  
[![Build Status](https://img.shields.io/badge/casetools-dcase-blue.svg)](https://travis-ci.org/joemccann/dillinger) [![Build Status](https://img.shields.io/badge/version-0.7.0-green.svg)](https://travis-ci.org/joemccann/dillinger)  
======

[Modelio](https://www.modelio.org/) is an open-source modelling project. The project in this repository contains the code and [binaries](https://github.com/ualegre/rcase/tree/master/rcase/target) of a Modelio module 
which has been created in order to aid developers of context-aware systems during the requirements elicitation development stage. It is complementary to another Modelio module for supporting the requirements elicitation stage of context-aware systems [RC-ASE](https://github.com/ualegre/rcase). It is recommended to install and use together with (at least) the open-source version of the [SysML Architect Modelio Module](http://store.modelio.org/resource/modules/sysml-architect-open-source.html).

The module includes the following features:
* Information Display Diagram
* Acquisition and Modelling Diagram
	* Code generation for modelling rules in mobile platforms (C-SPARQL)
	* Code generation for drives in mobile platform (Android)
* Context Reasoning Diagram
	* Code generation for verifying reasoning rules in the [NuSMV](http://nusmv.fbk.eu/) model checker
	* Code generation for rule specifications to be deployed in a stationary platform: [M Reasoner GUI](https://github.com/ualegre/mreasoner-gui)
	* Code generation for rules specifications to be deployed in a mobile platform: [Android Context Reasoner](https://github.com/ualegre/aContextReasoner)
* Deployment Diagram 
	* Stationary platforms 
	* Mobile platforms
* Code generation for all the previously mentioned features simultaneously

More information on the theoretical aspects of this module can be found in:
1. *Context-aware Computing for The Internet of Things: A Survey, IEEE communications surveys & tutorials,  Volume 16, Number 1, Pages 414-454, IEEE.*  
**Download here:** 
	* [Mirror 1](https://doi.org/10.1109/SURV.2013.042313.00197) 
	* [Mirror 2](https://arxiv.org/pdf/1305.0982)
2. *Supporting Context-Aware Engineering Based on Stream Reasoning, International and Interdisciplinary Conference on Modeling and Using Context (pp. 440-453). Springer, 2017, Cham.*  
**Download here:** 
	* [Mirror 1](https://doi.org/10.1007/978-3-319-57837-8_37) 
	* [Mirror 2](http://eprints.mdx.ac.uk/21461/)

## How-to use the content of this repository
### Instructions for users
Modelio is an open-source modelling tool which is freely available to be [downloaded](https://www.modelio.org/downloads/download-modelio.html) from its [official website](http://www.modelio.org). The current Modelio version for which this module is compatible is v3.7. Modelio, and therefore this module, is available for Linux, Windows and Mac. Follow the [Modelio Quick-start guide](https://www.modelio.org/quick-start-pages-35.html) provided in the official Modelio website to install the program in your preferred operating system. 

In order to install the DC-ASE Modelio module, follow the [official guide for working with modules](https://www.modelio.org/quick-start-pages-35/928-modelio/quick-start-35x/132-working-with-modules-35.html). You can download the latest RC-ASE Modelio module (.jmdac) directly from the [dcase/target](https://github.com/ualegre/dcase/blob/master/dcase/target/) folder of the repository. Other versions of the binaries can be directly downloaded below: 

* [DC-ASE v0.7.0](https://github.com/ualegre/dcase/raw/master/dcase/target/DCase_0.7.0.jmdac)

### Instructions for developers
For developing this module, it is recommended to have the Eclipse RCP neon with Maven (M2e) and Git (EGit) plugins correctly installed. Then, follow the steps:
1. Clone this repository to your local hard disk.
2. Open an Eclipse RCP neon instance in your preferred workspace (different than the folder where you have downloaded your repository)
3. Click on Import -> Existing Maven Projects -> (Select as root directory the folder where you have cloned your repo). EGit should automatically detect 
that you are using a repository.
4. To produce a new version of the .jmdac Modelio module file: Left click on the folder -> Run as -> 7 Maven install. 

## License 
This project is licensed under the [GNU General Public License v3.0](https://github.com/casetools/dcase/blob/master/LIBRARIES.md) 

### Third party libraries 
* [RCASE](https://github.com/ualegre/rcase), [GNU General Public License, Version 3.0](https://www.gnu.org/licenses/gpl-3.0.en.html)
* [JavaPoet-1.8](https://github.com/square/javapoet), [Apache License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0)

Part of the code related to the automatic generation of C-SPARQL rules, has been adopted from the [Context Modeller](https://github.com/deankramer/ContextModeller).

### Third party icons
The stereotypes of the UML profile introduced as part of the new diagrams available include different icons, which have been made and released for free use by the following users of [Flaticon](www.flaticon.com): 
* [Freepik](http://www.freepik.com/)
* [GraphicsBay](http://www.flaticon.com/authors/graphicsbay)
* [Ocha](http://www.flaticon.com/authors/ocha)
* [UIUXER](http://www.flaticon.com/authors/uiuxer)
* [Anton Saputro](https://www.flaticon.com/authors/anton-saputro)
* [SmashIcons](https://www.flaticon.com/authors/smashicons)
* [Eucalyp](https://www.flaticon.com/authors/eucalyp)
* [Amit Jakhu](http://www.flaticon.com/authors/amit-jakhu). 

## Developer Contact
This work has been created as part of the doctoral thesis contribution of Unai Alegre-Ibarra. The repository is open to pull-requests. 
* author: Unai Alegre-Ibarra
* e-mail: u.alegre@mdx.ac.uk




