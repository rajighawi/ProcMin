# ProcMin

### What is ProcMin?
ProcMin is an open source Java project that can do basic process mining techniques.

### What can ProcMin do?
Currently, ProcMic can:
* Load an event log (text files with traces and frequencies)
* Extract the footprint of the event log.
* Extract the direct succession matrix.
* Extract the dependency matrix.
* Mine a Petri Net (Workflow Net) from the log using `Alpha` algorithm.
* Mine a dependency graph from the log (given thresholds).

### How to get started?
Run `Main.java` in `main` package, this will launch a GUI that is simple and easy to use.


### Dependencies
ProcMin requires the following Java APIs:
* Guava: https://github.com/google/guava/wiki
* JUNG: http://jung.sourceforge.net/


