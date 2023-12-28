// Generated from C:/Users/Joao Laptop/OneDrive - Instituto Superior de Engenharia do Porto/Documents/sem4pi-22-23-27/base.core/src/main/java/eapli/base/question/domain\ExamANTLR.g4 by ANTLR 4.12.0
package eapli.base.question.domain.ANTLRExam;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class ExamANTLRParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.12.0", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		COLON=1, OPENBRACE=2, CLOSEBRACE=3, OPENBRACKETS=4, CLOSEBRACKETS=5, COMMA=6, 
		EXAM=7, ID=8, MINUS=9, ANSWER=10, ANSWERS=11, TITLE=12, OPENDATE=13, CLOSEDATE=14, 
		SECTIONS=15, OPTIONS=16, OPTION=17, STATEMENT=18, TABLE_A=19, TABLE_B=20, 
		QUOTATION=21, DIFFICULTY=22, TEXTUAL_DESCRIPTION=23, QUESTIONS=24, LIMIT_QUESTIONS=25, 
		SECTION=26, QUESTION=27, TYPE_OF_QUESTION=28, NUMERICAL=29, MULTIPLE_CHOICE=30, 
		MATCHING=31, TRUEORFALSE=32, MISSING_WORDS=33, SHORT_ANSWER=34, TRUE=35, 
		FALSE=36, TYPE=37, INT=38, DATE=39, WS=40, SPECIAL_CHARACTER=41, REAL=42, 
		STRING=43, SPACE=44, PHRASE=45, WORD=46, END=47;
	public static final int
		RULE_prog = 0, RULE_exam = 1, RULE_exam_body = 2, RULE_section = 3, RULE_question = 4, 
		RULE_body = 5, RULE_true_false = 6, RULE_missing_words = 7, RULE_missing_answer = 8, 
		RULE_numerical = 9, RULE_short_awnser = 10, RULE_multiple_choice = 11, 
		RULE_multiple_choice_option = 12, RULE_matching = 13, RULE_matching_tokenA = 14, 
		RULE_matching_tokenB = 15, RULE_matching_answers = 16;
	private static String[] makeRuleNames() {
		return new String[] {
			"prog", "exam", "exam_body", "section", "question", "body", "true_false", 
			"missing_words", "missing_answer", "numerical", "short_awnser", "multiple_choice", 
			"multiple_choice_option", "matching", "matching_tokenA", "matching_tokenB", 
			"matching_answers"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "':'", "'{'", "'}'", "'['", "']'", "','", "'Exam'", "'ID'", "'-'", 
			"'Answer'", "'Answers'", "'Title'", "'Open Date'", "'Close Date'", "'Sections'", 
			"'Options'", "'Option'", "'Statement'", "'Table A'", "'Table B'", "'Quotation'", 
			"'Difficulty'", "'Textual Description'", "'Questions'", "'Limit of Questions'", 
			"'Section'", "'Question'", "'Type of Question'", "'Numerical'", "'Multiple Choice'", 
			"'Matching'", "'True or False'", "'Missing Words'", "'Short Answer'", 
			"'True'", "'False'", "'Type'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "COLON", "OPENBRACE", "CLOSEBRACE", "OPENBRACKETS", "CLOSEBRACKETS", 
			"COMMA", "EXAM", "ID", "MINUS", "ANSWER", "ANSWERS", "TITLE", "OPENDATE", 
			"CLOSEDATE", "SECTIONS", "OPTIONS", "OPTION", "STATEMENT", "TABLE_A", 
			"TABLE_B", "QUOTATION", "DIFFICULTY", "TEXTUAL_DESCRIPTION", "QUESTIONS", 
			"LIMIT_QUESTIONS", "SECTION", "QUESTION", "TYPE_OF_QUESTION", "NUMERICAL", 
			"MULTIPLE_CHOICE", "MATCHING", "TRUEORFALSE", "MISSING_WORDS", "SHORT_ANSWER", 
			"TRUE", "FALSE", "TYPE", "INT", "DATE", "WS", "SPECIAL_CHARACTER", "REAL", 
			"STRING", "SPACE", "PHRASE", "WORD", "END"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "ExamANTLR.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public ExamANTLRParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProgContext extends ParserRuleContext {
		public ExamContext exam() {
			return getRuleContext(ExamContext.class,0);
		}
		public ProgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prog; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExamANTLRListener ) ((ExamANTLRListener)listener).enterProg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamANTLRListener ) ((ExamANTLRListener)listener).exitProg(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamANTLRVisitor ) return ((ExamANTLRVisitor<? extends T>)visitor).visitProg(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgContext prog() throws RecognitionException {
		ProgContext _localctx = new ProgContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_prog);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(34);
			exam();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExamContext extends ParserRuleContext {
		public TerminalNode EXAM() { return getToken(ExamANTLRParser.EXAM, 0); }
		public TerminalNode COLON() { return getToken(ExamANTLRParser.COLON, 0); }
		public TerminalNode OPENBRACE() { return getToken(ExamANTLRParser.OPENBRACE, 0); }
		public Exam_bodyContext exam_body() {
			return getRuleContext(Exam_bodyContext.class,0);
		}
		public TerminalNode CLOSEBRACE() { return getToken(ExamANTLRParser.CLOSEBRACE, 0); }
		public ExamContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exam; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExamANTLRListener ) ((ExamANTLRListener)listener).enterExam(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamANTLRListener ) ((ExamANTLRListener)listener).exitExam(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamANTLRVisitor ) return ((ExamANTLRVisitor<? extends T>)visitor).visitExam(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExamContext exam() throws RecognitionException {
		ExamContext _localctx = new ExamContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_exam);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(36);
			match(EXAM);
			setState(37);
			match(COLON);
			setState(38);
			match(OPENBRACE);
			setState(39);
			exam_body();
			setState(40);
			match(CLOSEBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Exam_bodyContext extends ParserRuleContext {
		public Token exam_id;
		public Token exam_title;
		public Token open_date;
		public Token close_date;
		public TerminalNode ID() { return getToken(ExamANTLRParser.ID, 0); }
		public List<TerminalNode> COLON() { return getTokens(ExamANTLRParser.COLON); }
		public TerminalNode COLON(int i) {
			return getToken(ExamANTLRParser.COLON, i);
		}
		public TerminalNode COMMA() { return getToken(ExamANTLRParser.COMMA, 0); }
		public TerminalNode TITLE() { return getToken(ExamANTLRParser.TITLE, 0); }
		public TerminalNode OPENDATE() { return getToken(ExamANTLRParser.OPENDATE, 0); }
		public TerminalNode CLOSEDATE() { return getToken(ExamANTLRParser.CLOSEDATE, 0); }
		public TerminalNode INT() { return getToken(ExamANTLRParser.INT, 0); }
		public TerminalNode PHRASE() { return getToken(ExamANTLRParser.PHRASE, 0); }
		public List<TerminalNode> DATE() { return getTokens(ExamANTLRParser.DATE); }
		public TerminalNode DATE(int i) {
			return getToken(ExamANTLRParser.DATE, i);
		}
		public List<SectionContext> section() {
			return getRuleContexts(SectionContext.class);
		}
		public SectionContext section(int i) {
			return getRuleContext(SectionContext.class,i);
		}
		public Exam_bodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exam_body; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExamANTLRListener ) ((ExamANTLRListener)listener).enterExam_body(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamANTLRListener ) ((ExamANTLRListener)listener).exitExam_body(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamANTLRVisitor ) return ((ExamANTLRVisitor<? extends T>)visitor).visitExam_body(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Exam_bodyContext exam_body() throws RecognitionException {
		Exam_bodyContext _localctx = new Exam_bodyContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_exam_body);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(42);
			match(ID);
			setState(43);
			match(COLON);
			setState(44);
			((Exam_bodyContext)_localctx).exam_id = match(INT);
			setState(45);
			match(COMMA);
			setState(46);
			match(TITLE);
			setState(47);
			match(COLON);
			setState(48);
			((Exam_bodyContext)_localctx).exam_title = match(PHRASE);
			setState(50); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(49);
				section();
				}
				}
				setState(52); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==SECTION );
			setState(54);
			match(OPENDATE);
			setState(55);
			match(COLON);
			setState(56);
			((Exam_bodyContext)_localctx).open_date = match(DATE);
			setState(57);
			match(CLOSEDATE);
			setState(58);
			match(COLON);
			setState(59);
			((Exam_bodyContext)_localctx).close_date = match(DATE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SectionContext extends ParserRuleContext {
		public Token section_id;
		public Token section_des;
		public Token section_difficulty;
		public Token limit;
		public TerminalNode SECTION() { return getToken(ExamANTLRParser.SECTION, 0); }
		public TerminalNode ID() { return getToken(ExamANTLRParser.ID, 0); }
		public List<TerminalNode> COLON() { return getTokens(ExamANTLRParser.COLON); }
		public TerminalNode COLON(int i) {
			return getToken(ExamANTLRParser.COLON, i);
		}
		public TerminalNode COMMA() { return getToken(ExamANTLRParser.COMMA, 0); }
		public TerminalNode OPENBRACE() { return getToken(ExamANTLRParser.OPENBRACE, 0); }
		public TerminalNode TEXTUAL_DESCRIPTION() { return getToken(ExamANTLRParser.TEXTUAL_DESCRIPTION, 0); }
		public TerminalNode DIFFICULTY() { return getToken(ExamANTLRParser.DIFFICULTY, 0); }
		public TerminalNode LIMIT_QUESTIONS() { return getToken(ExamANTLRParser.LIMIT_QUESTIONS, 0); }
		public TerminalNode QUESTIONS() { return getToken(ExamANTLRParser.QUESTIONS, 0); }
		public TerminalNode CLOSEBRACE() { return getToken(ExamANTLRParser.CLOSEBRACE, 0); }
		public List<TerminalNode> INT() { return getTokens(ExamANTLRParser.INT); }
		public TerminalNode INT(int i) {
			return getToken(ExamANTLRParser.INT, i);
		}
		public TerminalNode PHRASE() { return getToken(ExamANTLRParser.PHRASE, 0); }
		public List<QuestionContext> question() {
			return getRuleContexts(QuestionContext.class);
		}
		public QuestionContext question(int i) {
			return getRuleContext(QuestionContext.class,i);
		}
		public SectionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_section; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExamANTLRListener ) ((ExamANTLRListener)listener).enterSection(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamANTLRListener ) ((ExamANTLRListener)listener).exitSection(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamANTLRVisitor ) return ((ExamANTLRVisitor<? extends T>)visitor).visitSection(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SectionContext section() throws RecognitionException {
		SectionContext _localctx = new SectionContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_section);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(61);
			match(SECTION);
			setState(62);
			match(ID);
			setState(63);
			match(COLON);
			setState(64);
			((SectionContext)_localctx).section_id = match(INT);
			setState(65);
			match(COMMA);
			setState(66);
			match(OPENBRACE);
			setState(67);
			match(TEXTUAL_DESCRIPTION);
			setState(68);
			match(COLON);
			setState(69);
			((SectionContext)_localctx).section_des = match(PHRASE);
			setState(70);
			match(DIFFICULTY);
			setState(71);
			match(COLON);
			setState(72);
			((SectionContext)_localctx).section_difficulty = match(INT);
			setState(73);
			match(LIMIT_QUESTIONS);
			setState(74);
			match(COLON);
			setState(75);
			((SectionContext)_localctx).limit = match(INT);
			setState(76);
			match(QUESTIONS);
			setState(77);
			match(COLON);
			setState(79); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(78);
				question();
				}
				}
				setState(81); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==QUESTION );
			setState(83);
			match(CLOSEBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class QuestionContext extends ParserRuleContext {
		public Token question_id;
		public Token question_statement;
		public Token ques_quotation;
		public Token ques_difficulty;
		public TerminalNode QUESTION() { return getToken(ExamANTLRParser.QUESTION, 0); }
		public TerminalNode ID() { return getToken(ExamANTLRParser.ID, 0); }
		public List<TerminalNode> COLON() { return getTokens(ExamANTLRParser.COLON); }
		public TerminalNode COLON(int i) {
			return getToken(ExamANTLRParser.COLON, i);
		}
		public TerminalNode COMMA() { return getToken(ExamANTLRParser.COMMA, 0); }
		public TerminalNode STATEMENT() { return getToken(ExamANTLRParser.STATEMENT, 0); }
		public TerminalNode TYPE_OF_QUESTION() { return getToken(ExamANTLRParser.TYPE_OF_QUESTION, 0); }
		public BodyContext body() {
			return getRuleContext(BodyContext.class,0);
		}
		public TerminalNode QUOTATION() { return getToken(ExamANTLRParser.QUOTATION, 0); }
		public TerminalNode DIFFICULTY() { return getToken(ExamANTLRParser.DIFFICULTY, 0); }
		public List<TerminalNode> INT() { return getTokens(ExamANTLRParser.INT); }
		public TerminalNode INT(int i) {
			return getToken(ExamANTLRParser.INT, i);
		}
		public TerminalNode PHRASE() { return getToken(ExamANTLRParser.PHRASE, 0); }
		public QuestionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_question; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExamANTLRListener ) ((ExamANTLRListener)listener).enterQuestion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamANTLRListener ) ((ExamANTLRListener)listener).exitQuestion(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamANTLRVisitor ) return ((ExamANTLRVisitor<? extends T>)visitor).visitQuestion(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QuestionContext question() throws RecognitionException {
		QuestionContext _localctx = new QuestionContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_question);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(85);
			match(QUESTION);
			setState(86);
			match(ID);
			setState(87);
			match(COLON);
			setState(88);
			((QuestionContext)_localctx).question_id = match(INT);
			setState(89);
			match(COMMA);
			setState(90);
			match(STATEMENT);
			setState(91);
			match(COLON);
			setState(92);
			((QuestionContext)_localctx).question_statement = match(PHRASE);
			setState(93);
			match(TYPE_OF_QUESTION);
			setState(94);
			match(COLON);
			setState(95);
			body();
			setState(96);
			match(QUOTATION);
			setState(97);
			match(COLON);
			setState(98);
			((QuestionContext)_localctx).ques_quotation = match(INT);
			setState(99);
			match(DIFFICULTY);
			setState(100);
			match(COLON);
			setState(101);
			((QuestionContext)_localctx).ques_difficulty = match(INT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BodyContext extends ParserRuleContext {
		public Token type_question;
		public MatchingContext matching() {
			return getRuleContext(MatchingContext.class,0);
		}
		public TerminalNode MATCHING() { return getToken(ExamANTLRParser.MATCHING, 0); }
		public Multiple_choiceContext multiple_choice() {
			return getRuleContext(Multiple_choiceContext.class,0);
		}
		public TerminalNode MULTIPLE_CHOICE() { return getToken(ExamANTLRParser.MULTIPLE_CHOICE, 0); }
		public Short_awnserContext short_awnser() {
			return getRuleContext(Short_awnserContext.class,0);
		}
		public TerminalNode SHORT_ANSWER() { return getToken(ExamANTLRParser.SHORT_ANSWER, 0); }
		public NumericalContext numerical() {
			return getRuleContext(NumericalContext.class,0);
		}
		public TerminalNode NUMERICAL() { return getToken(ExamANTLRParser.NUMERICAL, 0); }
		public Missing_wordsContext missing_words() {
			return getRuleContext(Missing_wordsContext.class,0);
		}
		public TerminalNode MISSING_WORDS() { return getToken(ExamANTLRParser.MISSING_WORDS, 0); }
		public True_falseContext true_false() {
			return getRuleContext(True_falseContext.class,0);
		}
		public TerminalNode TRUEORFALSE() { return getToken(ExamANTLRParser.TRUEORFALSE, 0); }
		public BodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_body; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExamANTLRListener ) ((ExamANTLRListener)listener).enterBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamANTLRListener ) ((ExamANTLRListener)listener).exitBody(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamANTLRVisitor ) return ((ExamANTLRVisitor<? extends T>)visitor).visitBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BodyContext body() throws RecognitionException {
		BodyContext _localctx = new BodyContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_body);
		try {
			setState(115);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case MATCHING:
				enterOuterAlt(_localctx, 1);
				{
				setState(103);
				((BodyContext)_localctx).type_question = match(MATCHING);
				setState(104);
				matching();
				}
				break;
			case MULTIPLE_CHOICE:
				enterOuterAlt(_localctx, 2);
				{
				setState(105);
				((BodyContext)_localctx).type_question = match(MULTIPLE_CHOICE);
				setState(106);
				multiple_choice();
				}
				break;
			case SHORT_ANSWER:
				enterOuterAlt(_localctx, 3);
				{
				setState(107);
				((BodyContext)_localctx).type_question = match(SHORT_ANSWER);
				setState(108);
				short_awnser();
				}
				break;
			case NUMERICAL:
				enterOuterAlt(_localctx, 4);
				{
				setState(109);
				((BodyContext)_localctx).type_question = match(NUMERICAL);
				setState(110);
				numerical();
				}
				break;
			case MISSING_WORDS:
				enterOuterAlt(_localctx, 5);
				{
				setState(111);
				((BodyContext)_localctx).type_question = match(MISSING_WORDS);
				setState(112);
				missing_words();
				}
				break;
			case TRUEORFALSE:
				enterOuterAlt(_localctx, 6);
				{
				setState(113);
				((BodyContext)_localctx).type_question = match(TRUEORFALSE);
				setState(114);
				true_false();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class True_falseContext extends ParserRuleContext {
		public Token trueOrFalse_answer;
		public TerminalNode ANSWER() { return getToken(ExamANTLRParser.ANSWER, 0); }
		public TerminalNode COLON() { return getToken(ExamANTLRParser.COLON, 0); }
		public TerminalNode TRUE() { return getToken(ExamANTLRParser.TRUE, 0); }
		public TerminalNode FALSE() { return getToken(ExamANTLRParser.FALSE, 0); }
		public True_falseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_true_false; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExamANTLRListener ) ((ExamANTLRListener)listener).enterTrue_false(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamANTLRListener ) ((ExamANTLRListener)listener).exitTrue_false(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamANTLRVisitor ) return ((ExamANTLRVisitor<? extends T>)visitor).visitTrue_false(this);
			else return visitor.visitChildren(this);
		}
	}

	public final True_falseContext true_false() throws RecognitionException {
		True_falseContext _localctx = new True_falseContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_true_false);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(117);
			match(ANSWER);
			setState(118);
			match(COLON);
			setState(119);
			((True_falseContext)_localctx).trueOrFalse_answer = _input.LT(1);
			_la = _input.LA(1);
			if ( !(_la==TRUE || _la==FALSE) ) {
				((True_falseContext)_localctx).trueOrFalse_answer = (Token)_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Missing_wordsContext extends ParserRuleContext {
		public TerminalNode ANSWER() { return getToken(ExamANTLRParser.ANSWER, 0); }
		public TerminalNode COLON() { return getToken(ExamANTLRParser.COLON, 0); }
		public TerminalNode OPENBRACE() { return getToken(ExamANTLRParser.OPENBRACE, 0); }
		public TerminalNode CLOSEBRACE() { return getToken(ExamANTLRParser.CLOSEBRACE, 0); }
		public List<Missing_answerContext> missing_answer() {
			return getRuleContexts(Missing_answerContext.class);
		}
		public Missing_answerContext missing_answer(int i) {
			return getRuleContext(Missing_answerContext.class,i);
		}
		public Missing_wordsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_missing_words; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExamANTLRListener ) ((ExamANTLRListener)listener).enterMissing_words(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamANTLRListener ) ((ExamANTLRListener)listener).exitMissing_words(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamANTLRVisitor ) return ((ExamANTLRVisitor<? extends T>)visitor).visitMissing_words(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Missing_wordsContext missing_words() throws RecognitionException {
		Missing_wordsContext _localctx = new Missing_wordsContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_missing_words);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(121);
			match(ANSWER);
			setState(122);
			match(COLON);
			setState(123);
			match(OPENBRACE);
			setState(125); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(124);
				missing_answer();
				}
				}
				setState(127); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==ANSWER );
			setState(129);
			match(CLOSEBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Missing_answerContext extends ParserRuleContext {
		public Token miss_answer;
		public TerminalNode ANSWER() { return getToken(ExamANTLRParser.ANSWER, 0); }
		public TerminalNode INT() { return getToken(ExamANTLRParser.INT, 0); }
		public TerminalNode COLON() { return getToken(ExamANTLRParser.COLON, 0); }
		public TerminalNode WORD() { return getToken(ExamANTLRParser.WORD, 0); }
		public Missing_answerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_missing_answer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExamANTLRListener ) ((ExamANTLRListener)listener).enterMissing_answer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamANTLRListener ) ((ExamANTLRListener)listener).exitMissing_answer(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamANTLRVisitor ) return ((ExamANTLRVisitor<? extends T>)visitor).visitMissing_answer(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Missing_answerContext missing_answer() throws RecognitionException {
		Missing_answerContext _localctx = new Missing_answerContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_missing_answer);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(131);
			match(ANSWER);
			setState(132);
			match(INT);
			setState(133);
			match(COLON);
			setState(134);
			((Missing_answerContext)_localctx).miss_answer = match(WORD);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class NumericalContext extends ParserRuleContext {
		public Token num_answer;
		public TerminalNode ANSWER() { return getToken(ExamANTLRParser.ANSWER, 0); }
		public TerminalNode COLON() { return getToken(ExamANTLRParser.COLON, 0); }
		public TerminalNode REAL() { return getToken(ExamANTLRParser.REAL, 0); }
		public TerminalNode INT() { return getToken(ExamANTLRParser.INT, 0); }
		public NumericalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_numerical; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExamANTLRListener ) ((ExamANTLRListener)listener).enterNumerical(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamANTLRListener ) ((ExamANTLRListener)listener).exitNumerical(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamANTLRVisitor ) return ((ExamANTLRVisitor<? extends T>)visitor).visitNumerical(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NumericalContext numerical() throws RecognitionException {
		NumericalContext _localctx = new NumericalContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_numerical);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(136);
			match(ANSWER);
			setState(137);
			match(COLON);
			setState(138);
			((NumericalContext)_localctx).num_answer = _input.LT(1);
			_la = _input.LA(1);
			if ( !(_la==INT || _la==REAL) ) {
				((NumericalContext)_localctx).num_answer = (Token)_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Short_awnserContext extends ParserRuleContext {
		public Token short_answer;
		public TerminalNode ANSWER() { return getToken(ExamANTLRParser.ANSWER, 0); }
		public TerminalNode COLON() { return getToken(ExamANTLRParser.COLON, 0); }
		public TerminalNode PHRASE() { return getToken(ExamANTLRParser.PHRASE, 0); }
		public Short_awnserContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_short_awnser; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExamANTLRListener ) ((ExamANTLRListener)listener).enterShort_awnser(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamANTLRListener ) ((ExamANTLRListener)listener).exitShort_awnser(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamANTLRVisitor ) return ((ExamANTLRVisitor<? extends T>)visitor).visitShort_awnser(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Short_awnserContext short_awnser() throws RecognitionException {
		Short_awnserContext _localctx = new Short_awnserContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_short_awnser);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(140);
			match(ANSWER);
			setState(141);
			match(COLON);
			setState(142);
			((Short_awnserContext)_localctx).short_answer = match(PHRASE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Multiple_choiceContext extends ParserRuleContext {
		public Token multiple_choice_answer;
		public TerminalNode OPTIONS() { return getToken(ExamANTLRParser.OPTIONS, 0); }
		public List<TerminalNode> COLON() { return getTokens(ExamANTLRParser.COLON); }
		public TerminalNode COLON(int i) {
			return getToken(ExamANTLRParser.COLON, i);
		}
		public TerminalNode OPENBRACKETS() { return getToken(ExamANTLRParser.OPENBRACKETS, 0); }
		public TerminalNode CLOSEBRACKETS() { return getToken(ExamANTLRParser.CLOSEBRACKETS, 0); }
		public TerminalNode ANSWER() { return getToken(ExamANTLRParser.ANSWER, 0); }
		public TerminalNode INT() { return getToken(ExamANTLRParser.INT, 0); }
		public List<Multiple_choice_optionContext> multiple_choice_option() {
			return getRuleContexts(Multiple_choice_optionContext.class);
		}
		public Multiple_choice_optionContext multiple_choice_option(int i) {
			return getRuleContext(Multiple_choice_optionContext.class,i);
		}
		public Multiple_choiceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multiple_choice; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExamANTLRListener ) ((ExamANTLRListener)listener).enterMultiple_choice(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamANTLRListener ) ((ExamANTLRListener)listener).exitMultiple_choice(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamANTLRVisitor ) return ((ExamANTLRVisitor<? extends T>)visitor).visitMultiple_choice(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Multiple_choiceContext multiple_choice() throws RecognitionException {
		Multiple_choiceContext _localctx = new Multiple_choiceContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_multiple_choice);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(144);
			match(OPTIONS);
			setState(145);
			match(COLON);
			setState(146);
			match(OPENBRACKETS);
			setState(148); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(147);
				multiple_choice_option();
				}
				}
				setState(150); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==OPTION );
			setState(152);
			match(CLOSEBRACKETS);
			setState(153);
			match(ANSWER);
			setState(154);
			match(COLON);
			setState(155);
			((Multiple_choiceContext)_localctx).multiple_choice_answer = match(INT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Multiple_choice_optionContext extends ParserRuleContext {
		public Token multiple_option;
		public TerminalNode OPTION() { return getToken(ExamANTLRParser.OPTION, 0); }
		public TerminalNode INT() { return getToken(ExamANTLRParser.INT, 0); }
		public TerminalNode COLON() { return getToken(ExamANTLRParser.COLON, 0); }
		public TerminalNode PHRASE() { return getToken(ExamANTLRParser.PHRASE, 0); }
		public Multiple_choice_optionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multiple_choice_option; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExamANTLRListener ) ((ExamANTLRListener)listener).enterMultiple_choice_option(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamANTLRListener ) ((ExamANTLRListener)listener).exitMultiple_choice_option(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamANTLRVisitor ) return ((ExamANTLRVisitor<? extends T>)visitor).visitMultiple_choice_option(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Multiple_choice_optionContext multiple_choice_option() throws RecognitionException {
		Multiple_choice_optionContext _localctx = new Multiple_choice_optionContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_multiple_choice_option);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(157);
			match(OPTION);
			setState(158);
			match(INT);
			setState(159);
			match(COLON);
			setState(160);
			((Multiple_choice_optionContext)_localctx).multiple_option = match(PHRASE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class MatchingContext extends ParserRuleContext {
		public TerminalNode TABLE_A() { return getToken(ExamANTLRParser.TABLE_A, 0); }
		public List<TerminalNode> COLON() { return getTokens(ExamANTLRParser.COLON); }
		public TerminalNode COLON(int i) {
			return getToken(ExamANTLRParser.COLON, i);
		}
		public List<TerminalNode> OPENBRACKETS() { return getTokens(ExamANTLRParser.OPENBRACKETS); }
		public TerminalNode OPENBRACKETS(int i) {
			return getToken(ExamANTLRParser.OPENBRACKETS, i);
		}
		public List<TerminalNode> CLOSEBRACKETS() { return getTokens(ExamANTLRParser.CLOSEBRACKETS); }
		public TerminalNode CLOSEBRACKETS(int i) {
			return getToken(ExamANTLRParser.CLOSEBRACKETS, i);
		}
		public TerminalNode TABLE_B() { return getToken(ExamANTLRParser.TABLE_B, 0); }
		public TerminalNode ANSWER() { return getToken(ExamANTLRParser.ANSWER, 0); }
		public List<Matching_tokenAContext> matching_tokenA() {
			return getRuleContexts(Matching_tokenAContext.class);
		}
		public Matching_tokenAContext matching_tokenA(int i) {
			return getRuleContext(Matching_tokenAContext.class,i);
		}
		public List<Matching_tokenBContext> matching_tokenB() {
			return getRuleContexts(Matching_tokenBContext.class);
		}
		public Matching_tokenBContext matching_tokenB(int i) {
			return getRuleContext(Matching_tokenBContext.class,i);
		}
		public List<Matching_answersContext> matching_answers() {
			return getRuleContexts(Matching_answersContext.class);
		}
		public Matching_answersContext matching_answers(int i) {
			return getRuleContext(Matching_answersContext.class,i);
		}
		public MatchingContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_matching; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExamANTLRListener ) ((ExamANTLRListener)listener).enterMatching(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamANTLRListener ) ((ExamANTLRListener)listener).exitMatching(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamANTLRVisitor ) return ((ExamANTLRVisitor<? extends T>)visitor).visitMatching(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MatchingContext matching() throws RecognitionException {
		MatchingContext _localctx = new MatchingContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_matching);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(162);
			match(TABLE_A);
			setState(163);
			match(COLON);
			setState(164);
			match(OPENBRACKETS);
			setState(166); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(165);
				matching_tokenA();
				}
				}
				setState(168); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==STATEMENT );
			setState(170);
			match(CLOSEBRACKETS);
			setState(171);
			match(TABLE_B);
			setState(172);
			match(COLON);
			setState(173);
			match(OPENBRACKETS);
			setState(175); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(174);
				matching_tokenB();
				}
				}
				setState(177); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==STATEMENT );
			setState(179);
			match(CLOSEBRACKETS);
			setState(180);
			match(ANSWER);
			setState(181);
			match(COLON);
			setState(183); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(182);
				matching_answers();
				}
				}
				setState(185); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==TABLE_A );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Matching_tokenAContext extends ParserRuleContext {
		public Token match_answer;
		public TerminalNode STATEMENT() { return getToken(ExamANTLRParser.STATEMENT, 0); }
		public TerminalNode INT() { return getToken(ExamANTLRParser.INT, 0); }
		public TerminalNode COLON() { return getToken(ExamANTLRParser.COLON, 0); }
		public TerminalNode PHRASE() { return getToken(ExamANTLRParser.PHRASE, 0); }
		public Matching_tokenAContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_matching_tokenA; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExamANTLRListener ) ((ExamANTLRListener)listener).enterMatching_tokenA(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamANTLRListener ) ((ExamANTLRListener)listener).exitMatching_tokenA(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamANTLRVisitor ) return ((ExamANTLRVisitor<? extends T>)visitor).visitMatching_tokenA(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Matching_tokenAContext matching_tokenA() throws RecognitionException {
		Matching_tokenAContext _localctx = new Matching_tokenAContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_matching_tokenA);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(187);
			match(STATEMENT);
			setState(188);
			match(INT);
			setState(189);
			match(COLON);
			setState(190);
			((Matching_tokenAContext)_localctx).match_answer = match(PHRASE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Matching_tokenBContext extends ParserRuleContext {
		public Token match_answer;
		public TerminalNode STATEMENT() { return getToken(ExamANTLRParser.STATEMENT, 0); }
		public TerminalNode INT() { return getToken(ExamANTLRParser.INT, 0); }
		public TerminalNode COLON() { return getToken(ExamANTLRParser.COLON, 0); }
		public TerminalNode PHRASE() { return getToken(ExamANTLRParser.PHRASE, 0); }
		public Matching_tokenBContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_matching_tokenB; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExamANTLRListener ) ((ExamANTLRListener)listener).enterMatching_tokenB(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamANTLRListener ) ((ExamANTLRListener)listener).exitMatching_tokenB(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamANTLRVisitor ) return ((ExamANTLRVisitor<? extends T>)visitor).visitMatching_tokenB(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Matching_tokenBContext matching_tokenB() throws RecognitionException {
		Matching_tokenBContext _localctx = new Matching_tokenBContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_matching_tokenB);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(192);
			match(STATEMENT);
			setState(193);
			match(INT);
			setState(194);
			match(COLON);
			setState(195);
			((Matching_tokenBContext)_localctx).match_answer = match(PHRASE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Matching_answersContext extends ParserRuleContext {
		public Token tableA_ans;
		public Token tableB_ans;
		public TerminalNode TABLE_A() { return getToken(ExamANTLRParser.TABLE_A, 0); }
		public List<TerminalNode> COLON() { return getTokens(ExamANTLRParser.COLON); }
		public TerminalNode COLON(int i) {
			return getToken(ExamANTLRParser.COLON, i);
		}
		public TerminalNode MINUS() { return getToken(ExamANTLRParser.MINUS, 0); }
		public TerminalNode TABLE_B() { return getToken(ExamANTLRParser.TABLE_B, 0); }
		public List<TerminalNode> INT() { return getTokens(ExamANTLRParser.INT); }
		public TerminalNode INT(int i) {
			return getToken(ExamANTLRParser.INT, i);
		}
		public Matching_answersContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_matching_answers; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExamANTLRListener ) ((ExamANTLRListener)listener).enterMatching_answers(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamANTLRListener ) ((ExamANTLRListener)listener).exitMatching_answers(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamANTLRVisitor ) return ((ExamANTLRVisitor<? extends T>)visitor).visitMatching_answers(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Matching_answersContext matching_answers() throws RecognitionException {
		Matching_answersContext _localctx = new Matching_answersContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_matching_answers);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(197);
			match(TABLE_A);
			setState(198);
			match(COLON);
			setState(199);
			((Matching_answersContext)_localctx).tableA_ans = match(INT);
			setState(200);
			match(MINUS);
			setState(201);
			match(TABLE_B);
			setState(202);
			match(COLON);
			setState(203);
			((Matching_answersContext)_localctx).tableB_ans = match(INT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\u0004\u0001/\u00ce\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0004\u00023\b\u0002\u000b\u0002\f\u00024\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0003\u0001"+
		"\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001"+
		"\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001"+
		"\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0004\u0003P\b"+
		"\u0003\u000b\u0003\f\u0003Q\u0001\u0003\u0001\u0003\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0003\u0005t\b"+
		"\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0004\u0007~\b\u0007\u000b\u0007\f\u0007"+
		"\u007f\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b"+
		"\u0001\t\u0001\t\u0001\t\u0001\t\u0001\n\u0001\n\u0001\n\u0001\n\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0004\u000b\u0095\b\u000b\u000b"+
		"\u000b\f\u000b\u0096\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\r\u0001\r\u0001\r"+
		"\u0001\r\u0004\r\u00a7\b\r\u000b\r\f\r\u00a8\u0001\r\u0001\r\u0001\r\u0001"+
		"\r\u0001\r\u0004\r\u00b0\b\r\u000b\r\f\r\u00b1\u0001\r\u0001\r\u0001\r"+
		"\u0001\r\u0004\r\u00b8\b\r\u000b\r\f\r\u00b9\u0001\u000e\u0001\u000e\u0001"+
		"\u000e\u0001\u000e\u0001\u000e\u0001\u000f\u0001\u000f\u0001\u000f\u0001"+
		"\u000f\u0001\u000f\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001"+
		"\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0000\u0000\u0011"+
		"\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a"+
		"\u001c\u001e \u0000\u0002\u0001\u0000#$\u0002\u0000&&**\u00c8\u0000\""+
		"\u0001\u0000\u0000\u0000\u0002$\u0001\u0000\u0000\u0000\u0004*\u0001\u0000"+
		"\u0000\u0000\u0006=\u0001\u0000\u0000\u0000\bU\u0001\u0000\u0000\u0000"+
		"\ns\u0001\u0000\u0000\u0000\fu\u0001\u0000\u0000\u0000\u000ey\u0001\u0000"+
		"\u0000\u0000\u0010\u0083\u0001\u0000\u0000\u0000\u0012\u0088\u0001\u0000"+
		"\u0000\u0000\u0014\u008c\u0001\u0000\u0000\u0000\u0016\u0090\u0001\u0000"+
		"\u0000\u0000\u0018\u009d\u0001\u0000\u0000\u0000\u001a\u00a2\u0001\u0000"+
		"\u0000\u0000\u001c\u00bb\u0001\u0000\u0000\u0000\u001e\u00c0\u0001\u0000"+
		"\u0000\u0000 \u00c5\u0001\u0000\u0000\u0000\"#\u0003\u0002\u0001\u0000"+
		"#\u0001\u0001\u0000\u0000\u0000$%\u0005\u0007\u0000\u0000%&\u0005\u0001"+
		"\u0000\u0000&\'\u0005\u0002\u0000\u0000\'(\u0003\u0004\u0002\u0000()\u0005"+
		"\u0003\u0000\u0000)\u0003\u0001\u0000\u0000\u0000*+\u0005\b\u0000\u0000"+
		"+,\u0005\u0001\u0000\u0000,-\u0005&\u0000\u0000-.\u0005\u0006\u0000\u0000"+
		"./\u0005\f\u0000\u0000/0\u0005\u0001\u0000\u000002\u0005-\u0000\u0000"+
		"13\u0003\u0006\u0003\u000021\u0001\u0000\u0000\u000034\u0001\u0000\u0000"+
		"\u000042\u0001\u0000\u0000\u000045\u0001\u0000\u0000\u000056\u0001\u0000"+
		"\u0000\u000067\u0005\r\u0000\u000078\u0005\u0001\u0000\u000089\u0005\'"+
		"\u0000\u00009:\u0005\u000e\u0000\u0000:;\u0005\u0001\u0000\u0000;<\u0005"+
		"\'\u0000\u0000<\u0005\u0001\u0000\u0000\u0000=>\u0005\u001a\u0000\u0000"+
		">?\u0005\b\u0000\u0000?@\u0005\u0001\u0000\u0000@A\u0005&\u0000\u0000"+
		"AB\u0005\u0006\u0000\u0000BC\u0005\u0002\u0000\u0000CD\u0005\u0017\u0000"+
		"\u0000DE\u0005\u0001\u0000\u0000EF\u0005-\u0000\u0000FG\u0005\u0016\u0000"+
		"\u0000GH\u0005\u0001\u0000\u0000HI\u0005&\u0000\u0000IJ\u0005\u0019\u0000"+
		"\u0000JK\u0005\u0001\u0000\u0000KL\u0005&\u0000\u0000LM\u0005\u0018\u0000"+
		"\u0000MO\u0005\u0001\u0000\u0000NP\u0003\b\u0004\u0000ON\u0001\u0000\u0000"+
		"\u0000PQ\u0001\u0000\u0000\u0000QO\u0001\u0000\u0000\u0000QR\u0001\u0000"+
		"\u0000\u0000RS\u0001\u0000\u0000\u0000ST\u0005\u0003\u0000\u0000T\u0007"+
		"\u0001\u0000\u0000\u0000UV\u0005\u001b\u0000\u0000VW\u0005\b\u0000\u0000"+
		"WX\u0005\u0001\u0000\u0000XY\u0005&\u0000\u0000YZ\u0005\u0006\u0000\u0000"+
		"Z[\u0005\u0012\u0000\u0000[\\\u0005\u0001\u0000\u0000\\]\u0005-\u0000"+
		"\u0000]^\u0005\u001c\u0000\u0000^_\u0005\u0001\u0000\u0000_`\u0003\n\u0005"+
		"\u0000`a\u0005\u0015\u0000\u0000ab\u0005\u0001\u0000\u0000bc\u0005&\u0000"+
		"\u0000cd\u0005\u0016\u0000\u0000de\u0005\u0001\u0000\u0000ef\u0005&\u0000"+
		"\u0000f\t\u0001\u0000\u0000\u0000gh\u0005\u001f\u0000\u0000ht\u0003\u001a"+
		"\r\u0000ij\u0005\u001e\u0000\u0000jt\u0003\u0016\u000b\u0000kl\u0005\""+
		"\u0000\u0000lt\u0003\u0014\n\u0000mn\u0005\u001d\u0000\u0000nt\u0003\u0012"+
		"\t\u0000op\u0005!\u0000\u0000pt\u0003\u000e\u0007\u0000qr\u0005 \u0000"+
		"\u0000rt\u0003\f\u0006\u0000sg\u0001\u0000\u0000\u0000si\u0001\u0000\u0000"+
		"\u0000sk\u0001\u0000\u0000\u0000sm\u0001\u0000\u0000\u0000so\u0001\u0000"+
		"\u0000\u0000sq\u0001\u0000\u0000\u0000t\u000b\u0001\u0000\u0000\u0000"+
		"uv\u0005\n\u0000\u0000vw\u0005\u0001\u0000\u0000wx\u0007\u0000\u0000\u0000"+
		"x\r\u0001\u0000\u0000\u0000yz\u0005\n\u0000\u0000z{\u0005\u0001\u0000"+
		"\u0000{}\u0005\u0002\u0000\u0000|~\u0003\u0010\b\u0000}|\u0001\u0000\u0000"+
		"\u0000~\u007f\u0001\u0000\u0000\u0000\u007f}\u0001\u0000\u0000\u0000\u007f"+
		"\u0080\u0001\u0000\u0000\u0000\u0080\u0081\u0001\u0000\u0000\u0000\u0081"+
		"\u0082\u0005\u0003\u0000\u0000\u0082\u000f\u0001\u0000\u0000\u0000\u0083"+
		"\u0084\u0005\n\u0000\u0000\u0084\u0085\u0005&\u0000\u0000\u0085\u0086"+
		"\u0005\u0001\u0000\u0000\u0086\u0087\u0005.\u0000\u0000\u0087\u0011\u0001"+
		"\u0000\u0000\u0000\u0088\u0089\u0005\n\u0000\u0000\u0089\u008a\u0005\u0001"+
		"\u0000\u0000\u008a\u008b\u0007\u0001\u0000\u0000\u008b\u0013\u0001\u0000"+
		"\u0000\u0000\u008c\u008d\u0005\n\u0000\u0000\u008d\u008e\u0005\u0001\u0000"+
		"\u0000\u008e\u008f\u0005-\u0000\u0000\u008f\u0015\u0001\u0000\u0000\u0000"+
		"\u0090\u0091\u0005\u0010\u0000\u0000\u0091\u0092\u0005\u0001\u0000\u0000"+
		"\u0092\u0094\u0005\u0004\u0000\u0000\u0093\u0095\u0003\u0018\f\u0000\u0094"+
		"\u0093\u0001\u0000\u0000\u0000\u0095\u0096\u0001\u0000\u0000\u0000\u0096"+
		"\u0094\u0001\u0000\u0000\u0000\u0096\u0097\u0001\u0000\u0000\u0000\u0097"+
		"\u0098\u0001\u0000\u0000\u0000\u0098\u0099\u0005\u0005\u0000\u0000\u0099"+
		"\u009a\u0005\n\u0000\u0000\u009a\u009b\u0005\u0001\u0000\u0000\u009b\u009c"+
		"\u0005&\u0000\u0000\u009c\u0017\u0001\u0000\u0000\u0000\u009d\u009e\u0005"+
		"\u0011\u0000\u0000\u009e\u009f\u0005&\u0000\u0000\u009f\u00a0\u0005\u0001"+
		"\u0000\u0000\u00a0\u00a1\u0005-\u0000\u0000\u00a1\u0019\u0001\u0000\u0000"+
		"\u0000\u00a2\u00a3\u0005\u0013\u0000\u0000\u00a3\u00a4\u0005\u0001\u0000"+
		"\u0000\u00a4\u00a6\u0005\u0004\u0000\u0000\u00a5\u00a7\u0003\u001c\u000e"+
		"\u0000\u00a6\u00a5\u0001\u0000\u0000\u0000\u00a7\u00a8\u0001\u0000\u0000"+
		"\u0000\u00a8\u00a6\u0001\u0000\u0000\u0000\u00a8\u00a9\u0001\u0000\u0000"+
		"\u0000\u00a9\u00aa\u0001\u0000\u0000\u0000\u00aa\u00ab\u0005\u0005\u0000"+
		"\u0000\u00ab\u00ac\u0005\u0014\u0000\u0000\u00ac\u00ad\u0005\u0001\u0000"+
		"\u0000\u00ad\u00af\u0005\u0004\u0000\u0000\u00ae\u00b0\u0003\u001e\u000f"+
		"\u0000\u00af\u00ae\u0001\u0000\u0000\u0000\u00b0\u00b1\u0001\u0000\u0000"+
		"\u0000\u00b1\u00af\u0001\u0000\u0000\u0000\u00b1\u00b2\u0001\u0000\u0000"+
		"\u0000\u00b2\u00b3\u0001\u0000\u0000\u0000\u00b3\u00b4\u0005\u0005\u0000"+
		"\u0000\u00b4\u00b5\u0005\n\u0000\u0000\u00b5\u00b7\u0005\u0001\u0000\u0000"+
		"\u00b6\u00b8\u0003 \u0010\u0000\u00b7\u00b6\u0001\u0000\u0000\u0000\u00b8"+
		"\u00b9\u0001\u0000\u0000\u0000\u00b9\u00b7\u0001\u0000\u0000\u0000\u00b9"+
		"\u00ba\u0001\u0000\u0000\u0000\u00ba\u001b\u0001\u0000\u0000\u0000\u00bb"+
		"\u00bc\u0005\u0012\u0000\u0000\u00bc\u00bd\u0005&\u0000\u0000\u00bd\u00be"+
		"\u0005\u0001\u0000\u0000\u00be\u00bf\u0005-\u0000\u0000\u00bf\u001d\u0001"+
		"\u0000\u0000\u0000\u00c0\u00c1\u0005\u0012\u0000\u0000\u00c1\u00c2\u0005"+
		"&\u0000\u0000\u00c2\u00c3\u0005\u0001\u0000\u0000\u00c3\u00c4\u0005-\u0000"+
		"\u0000\u00c4\u001f\u0001\u0000\u0000\u0000\u00c5\u00c6\u0005\u0013\u0000"+
		"\u0000\u00c6\u00c7\u0005\u0001\u0000\u0000\u00c7\u00c8\u0005&\u0000\u0000"+
		"\u00c8\u00c9\u0005\t\u0000\u0000\u00c9\u00ca\u0005\u0014\u0000\u0000\u00ca"+
		"\u00cb\u0005\u0001\u0000\u0000\u00cb\u00cc\u0005&\u0000\u0000\u00cc!\u0001"+
		"\u0000\u0000\u0000\b4Qs\u007f\u0096\u00a8\u00b1\u00b9";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}