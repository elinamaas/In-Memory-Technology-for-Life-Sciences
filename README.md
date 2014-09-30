Annotation guidelines for de-identification of medical records
Amber Stubbs and Ozlem Uzuner Last updated: April 1, 2014
HIPAA requires that patient medical records have all identifying information removed in order to protect patient privacy. There are 18 categories of Protected Health Information (PHI) identifiers of the patient or of relatives, employers, or household members of the patient that must be removed in order for a file to be considered de-identified.
In order to de-identify the records, each file must have the PHI marked up so that it can be removed/replaced later. This will be done using the annotation software MAE, and all PHI will be given an XML tag indicating its category and type, where applicable.


To run the system:
1. Define two similar tables in HANA to test ans training set.
	2 columns (id and text)
2. Run the application: medical records will be pasted to the tables, after it utomatically will run SQL script and create indexis. After indexis are created, found tags will be extracted to the res/results folder.
3. in folder res you can find evaluation folder. Run the python script to see the evaluation.
