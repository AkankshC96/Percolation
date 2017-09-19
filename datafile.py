#This program will receive multiple .fastq files and depending on the 
#required sequence and SNPs, will output new .fastq files that only have the 
#sequences required for further analysis

import sys
import errno
import os
import fileinput
import re

#establish correct files for input to output
iF = raw_input("Enter input file name: ")
oF = raw_input("Enter output file name: ")
iF2 = raw_input("Enter second input file name: ")
oF2 = raw_input("Enter second output file name: ")



def filechange(inputfile, outputfile):
	#count keeps track of position in .fastq file
	count=0
	infile = open(inputfile, 'r') 
	outfile = open(outputfile, 'w') 
	#lists are created from original .fastq files because it is easier to modify the lists then the actual files
	infileList = list(infile)
	outfileList = list()
	#find correct sequences to insert into new output file
	for line in infileList: 
		if "CCTCATCT" in line or "AGATGAGG" in line:
			#first sequence has different formatting so slightly different parameters
			if count < 2:
				outfileList+=infileList[count-1:count+2]
				count+=1
			else:
			#add all parameters of sequence to new output file
				outfileList+=infileList[count-2:count+2]
				count+=1
		else:
		#if sequence does not have correct SNPs, then do not add to output file
			count+=1
			pass
	#move all values from list to final .fastq file
	for value in outfileList:
		outfile.write(value)
	infile.close()
	outfile.close()
	return

filechange(iF, oF)
filechange(iF2, oF2)








