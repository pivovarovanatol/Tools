package com.pivovarovanatol.Tools;

import java.io.FileNotFoundException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestRegex {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		
		final String floatingPointNumberRegEx = "[-+]?[0-9]*\\.?[0-9]+([eE][-+]?[0-9]+)?";
		final String hexadecimalNumberRegEx = "0x[0-9a-fA-F]+";
		final String strOperatorLineRegex = "\\s*# " + //zero or more leading spaces up to the '#' sign
											"(?<operatorname>.*?)\\s*" + //required group, operator_name (i.e. 'PROJECT' or 'GROUP'), operator_name deemed to end when '(opId:...)' starts.  //?<operatorname>
											// "\\(opId:(?:[LP]:)?(?<operatorid>\\d+)\\).*?\\s*" + //required group(2) is the operator_id with ignored group of :L: or :P:     //?<operatorid>
											"\\(opId:(?:[LP]:)(?<operatorid>\\d+)\\).*?\\s*" + //required group(2) is the operator_id with ignored group of :L: or :P:     //?<operatorid>
											//"(TABLE.used.cols:(?<tableusedcolls>.*?\\s*))?" + //optional table used colls  //?<tableusedcolls>
											//"(TABLE.histo.cols:(?<tablehistocolls>.*?\\s*))?" + //optional table histo colls  //?<tablehistocolls>
											//"(TABLE.key.joined.cols:(?<tablekeyjoinedcolls>.*?\\s*))?" + //optional table key joined colls  //?<tablekeyjoinedcolls>
											"(\\((?<objectid>\\d+)\\)\\s*)?" + //optional group object id  //?<objectid>
											"(?:(?!(input_size:|olap_fact_rel:|result_size:|output_column_size:|cost:|lowerbound:|subtree_cost:|result_order:|enumerated_by:|addr:|address:)).)*"	+ //required, non-capturing group to skip over the operator_details, up to, but not including, one of the following: 'result_size:', 'subtree_cost:', 'result_order:', 'enumerated_by:' 'address:', '
											"([LOGICAL|PHYSICAL])?" + // optional group - some oeprators types like [LOGICAL] 
											"(input_size:(?<inputsize>"+floatingPointNumberRegEx+").*?\\s*)?" + //optional group input_size    //?<inputsize>
											"(table_location:(?<tablelocation>).*)?" + //optional group table_location    //?<tablelocation>
											"(.(LOGICAL|PHYSICAL).*?\\s*)?" + //optional LOGICAL or PHYSICAL operator
											"(olap_fact_rel:(?<olapfactrel>\\d+).*?\\s*)?" + //optional group olap_fact_rel                      //?<olapfactrel>
											"(result_size:(?<resultsize>"+floatingPointNumberRegEx+").*?\\s*)?" + //optional group result_size  //?<resultsize>
											"(output_column_size:(?<outputcolsize>"+floatingPointNumberRegEx+").*?\\s*)?" + //optional group output_column_size   //?<outputcolumnsize>
											"(cost:(?<cost>(DBL_MAX|"+floatingPointNumberRegEx+")).*?\\s*)?" +  //optional group cost                  //?<cost>
											"(lowerbound:(?<lowerbound>"+floatingPointNumberRegEx+").*?\\s*)?" +  //optional group lowerbound      //?<lowerbound>
											
							//				TODO: subtree_cost:DBL_MAX is apparently possible to encounter, and probably unhandled											
											"(subtree_cost:(?<subtreecost>(DBL_MAX|"+floatingPointNumberRegEx+"))\\s*)?" +									//?<subtreecost>  
											"(result_order:(?<resultorder>.*?)\\s*)?" +  //optional group result_order							//?<resultorder>
											"(enumerated_by:(?<enumeratedby>.*?)\\s*)?" + //optional group, enumerated_by							//?<enumeratedby>
											"((addr|address|rel_addr):(?<memoryaddr>" + hexadecimalNumberRegEx + ")\\s*)?" + //optional group for memory address  //?<memoryaddress>
											"(Pushdown blocker::(?<pushdownblocker>.*?)\\s*)?" + //optional group pushdownblocker						//?<pushdownblocker>
											"(\\s+at\\s+\\[(?<host>.*?)\\])?"; //optional group host											//?<host>

		
		
		//String operator_line = "    # TABLE SOPIBP00PIBP00DIBP00P (CALC VIEW) (0) (opId:L:2) TABLE used cols::  TABLE histo cols::  TABLE key joined cols::  input_size:-1 table_location: vadbzyo:30203|0  [LOGICAL] result_size:-1 rel_addr:0x00007f602886d570";
	    
	    String operator_line = "# PROJECT (opId:L:0) (opId:P:22) ((0, 10000000))  [LOGICAL] result_size:1 lowerbound_cost:1.08892035181818e-05 rel_addr:0x00004d005ed84a30  [PHYSICAL] cost:1.08892035181818e-05 physicalop_addr:0x00004d005ed89db0 at [apbhphana:30003|0]";
		
		final Pattern patOperatorLine = Pattern.compile(strOperatorLineRegex); //,Pattern.DOTALL);
		Matcher matchOperatorLine = patOperatorLine.matcher(operator_line);
		
		System.out.println("String to match: " + operator_line);
		System.out.println("Matches? " + matchOperatorLine.matches());

		System.out.println("Found operatorname: " + matchOperatorLine.group("operatorname"));
		System.out.println("Found operatorid: " + matchOperatorLine.group("operatorid"));
		System.out.println("Found inputsize: " + matchOperatorLine.group("inputsize"));
		System.out.println("Found tablelocation: " + matchOperatorLine.group("tablelocation"));
		System.out.println("Found olapfactrel: " + matchOperatorLine.group("olapfactrel"));
		System.out.println("Found resultsize: " + matchOperatorLine.group("resultsize"));
		System.out.println("Found outputcolsize: " + matchOperatorLine.group("outputcolsize"));
		System.out.println("Found cost: " + matchOperatorLine.group("cost"));
		System.out.println("Found lowerbound: " + matchOperatorLine.group("lowerbound"));
		System.out.println("Found subtreecost: " + matchOperatorLine.group("subtreecost"));
		System.out.println("Found resultorder: " + matchOperatorLine.group("resultorder"));
		System.out.println("Found enumeratedby: " + matchOperatorLine.group("enumeratedby"));
		System.out.println("Found memoryaddr: " + matchOperatorLine.group("memoryaddr"));
		System.out.println("Found pushdownblocker: " + matchOperatorLine.group("pushdownblocker"));
		System.out.println("Found host: " + matchOperatorLine.group("host"));

		
	}
	

}
