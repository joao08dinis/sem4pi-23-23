grammar ExamANTLR;

prog: exam;

exam: EXAM COLON OPENBRACE exam_body CLOSEBRACE;

exam_body: ID COLON exam_id=INT COMMA TITLE COLON exam_title=PHRASE section+ OPENDATE COLON open_date=DATE CLOSEDATE COLON close_date=DATE;

section: SECTION ID COLON section_id=INT COMMA OPENBRACE TEXTUAL_DESCRIPTION COLON section_des=PHRASE DIFFICULTY COLON section_difficulty=INT LIMIT_QUESTIONS COLON limit=INT QUESTIONS COLON question+ CLOSEBRACE;

question: QUESTION ID COLON question_id=INT COMMA STATEMENT COLON question_statement=PHRASE TYPE_OF_QUESTION COLON body QUOTATION COLON ques_quotation=INT DIFFICULTY COLON ques_difficulty=INT;

body: type_question=MATCHING matching
    | type_question=MULTIPLE_CHOICE multiple_choice
    | type_question=SHORT_ANSWER short_awnser
    | type_question=NUMERICAL numerical
    | type_question=MISSING_WORDS missing_words
    | type_question=TRUEORFALSE true_false;

true_false: ANSWER COLON trueOrFalse_answer = (TRUE | FALSE) ;

missing_words: ANSWER COLON OPENBRACE missing_answer+ CLOSEBRACE;

missing_answer : ANSWER INT COLON miss_answer=WORD;


numerical: ANSWER COLON num_answer=(REAL|INT);


short_awnser: ANSWER COLON short_answer=PHRASE;


multiple_choice: OPTIONS COLON OPENBRACKETS multiple_choice_option+ CLOSEBRACKETS ANSWER COLON multiple_choice_answer=INT;

multiple_choice_option: OPTION INT COLON multiple_option=PHRASE;


matching: TABLE_A COLON OPENBRACKETS matching_tokenA+ CLOSEBRACKETS TABLE_B COLON OPENBRACKETS matching_tokenB+ CLOSEBRACKETS ANSWER COLON matching_answers+;

matching_tokenA: STATEMENT INT COLON match_answer=PHRASE;

matching_tokenB: STATEMENT INT COLON match_answer=PHRASE;

matching_answers: TABLE_A COLON tableA_ans=INT MINUS TABLE_B COLON tableB_ans=INT;

COLON : ':';
OPENBRACE : '{';
CLOSEBRACE : '}';
OPENBRACKETS : '[';
CLOSEBRACKETS : ']';
COMMA : ',';
EXAM : 'Exam';
ID : 'ID';
MINUS : '-';
ANSWER : 'Answer';
ANSWERS : 'Answers';
TITLE : 'Title';
OPENDATE : 'Open Date';
CLOSEDATE : 'Close Date';
SECTIONS : 'Sections';
OPTIONS : 'Options';
OPTION : 'Option';
STATEMENT : 'Statement';
TABLE_A : 'Table A';
TABLE_B : 'Table B';
QUOTATION : 'Quotation';
DIFFICULTY : 'Difficulty';
TEXTUAL_DESCRIPTION : 'Textual Description';
QUESTIONS : 'Questions';
LIMIT_QUESTIONS : 'Limit of Questions';
SECTION : 'Section';
QUESTION : 'Question';
TYPE_OF_QUESTION : 'Type of Question';
NUMERICAL : 'Numerical';
MULTIPLE_CHOICE : 'Multiple Choice';
MATCHING : 'Matching';
TRUEORFALSE : 'True or False';
MISSING_WORDS : 'Missing Words';
SHORT_ANSWER : 'Short Answer';
TRUE : 'True';
FALSE : 'False';
TYPE : 'Type';
INT : [0-9]+;
DATE : (('0' [0-9]) | ('1' [0-9]) | ('2' [0-9]) | ('3' [0-1])) '/' (('0' [0-9]) | ('1' [0-2])) '/' (([0-9] [0-9] [0-9] [0-9])) ' ' ([0-9][0-9]) COLON ([0-9][0-9]);
WS: [\r\n ]+ -> skip;
SPECIAL_CHARACTER: [_()+/\-'#"] -> skip;
REAL: INT'.'INT;
STRING : [a-zA-Z]+[0-9]+ | '_' | '\'' ;
SPACE: [ \t] -> skip;
PHRASE: SPECIAL_CHARACTER? (WORD|INT) WORD (WORD|INT|SPACE|SPECIAL_CHARACTER|COMMA)* END SPECIAL_CHARACTER? ;
WORD: [A-Za-z]+;
END: [.!?;];

