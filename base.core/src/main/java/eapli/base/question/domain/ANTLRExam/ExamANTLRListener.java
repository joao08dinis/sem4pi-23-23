// Generated from C:/Users/Joao Laptop/OneDrive - Instituto Superior de Engenharia do Porto/Documents/sem4pi-22-23-27/base.core/src/main/java/eapli/base/question/domain\ExamANTLR.g4 by ANTLR 4.12.0
package eapli.base.question.domain.ANTLRExam;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ExamANTLRParser}.
 */
public interface ExamANTLRListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ExamANTLRParser#prog}.
	 * @param ctx the parse tree
	 */
	void enterProg(ExamANTLRParser.ProgContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamANTLRParser#prog}.
	 * @param ctx the parse tree
	 */
	void exitProg(ExamANTLRParser.ProgContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExamANTLRParser#exam}.
	 * @param ctx the parse tree
	 */
	void enterExam(ExamANTLRParser.ExamContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamANTLRParser#exam}.
	 * @param ctx the parse tree
	 */
	void exitExam(ExamANTLRParser.ExamContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExamANTLRParser#exam_body}.
	 * @param ctx the parse tree
	 */
	void enterExam_body(ExamANTLRParser.Exam_bodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamANTLRParser#exam_body}.
	 * @param ctx the parse tree
	 */
	void exitExam_body(ExamANTLRParser.Exam_bodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExamANTLRParser#section}.
	 * @param ctx the parse tree
	 */
	void enterSection(ExamANTLRParser.SectionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamANTLRParser#section}.
	 * @param ctx the parse tree
	 */
	void exitSection(ExamANTLRParser.SectionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExamANTLRParser#question}.
	 * @param ctx the parse tree
	 */
	void enterQuestion(ExamANTLRParser.QuestionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamANTLRParser#question}.
	 * @param ctx the parse tree
	 */
	void exitQuestion(ExamANTLRParser.QuestionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExamANTLRParser#body}.
	 * @param ctx the parse tree
	 */
	void enterBody(ExamANTLRParser.BodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamANTLRParser#body}.
	 * @param ctx the parse tree
	 */
	void exitBody(ExamANTLRParser.BodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExamANTLRParser#true_false}.
	 * @param ctx the parse tree
	 */
	void enterTrue_false(ExamANTLRParser.True_falseContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamANTLRParser#true_false}.
	 * @param ctx the parse tree
	 */
	void exitTrue_false(ExamANTLRParser.True_falseContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExamANTLRParser#missing_words}.
	 * @param ctx the parse tree
	 */
	void enterMissing_words(ExamANTLRParser.Missing_wordsContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamANTLRParser#missing_words}.
	 * @param ctx the parse tree
	 */
	void exitMissing_words(ExamANTLRParser.Missing_wordsContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExamANTLRParser#missing_answer}.
	 * @param ctx the parse tree
	 */
	void enterMissing_answer(ExamANTLRParser.Missing_answerContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamANTLRParser#missing_answer}.
	 * @param ctx the parse tree
	 */
	void exitMissing_answer(ExamANTLRParser.Missing_answerContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExamANTLRParser#numerical}.
	 * @param ctx the parse tree
	 */
	void enterNumerical(ExamANTLRParser.NumericalContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamANTLRParser#numerical}.
	 * @param ctx the parse tree
	 */
	void exitNumerical(ExamANTLRParser.NumericalContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExamANTLRParser#short_awnser}.
	 * @param ctx the parse tree
	 */
	void enterShort_awnser(ExamANTLRParser.Short_awnserContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamANTLRParser#short_awnser}.
	 * @param ctx the parse tree
	 */
	void exitShort_awnser(ExamANTLRParser.Short_awnserContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExamANTLRParser#multiple_choice}.
	 * @param ctx the parse tree
	 */
	void enterMultiple_choice(ExamANTLRParser.Multiple_choiceContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamANTLRParser#multiple_choice}.
	 * @param ctx the parse tree
	 */
	void exitMultiple_choice(ExamANTLRParser.Multiple_choiceContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExamANTLRParser#multiple_choice_option}.
	 * @param ctx the parse tree
	 */
	void enterMultiple_choice_option(ExamANTLRParser.Multiple_choice_optionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamANTLRParser#multiple_choice_option}.
	 * @param ctx the parse tree
	 */
	void exitMultiple_choice_option(ExamANTLRParser.Multiple_choice_optionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExamANTLRParser#matching}.
	 * @param ctx the parse tree
	 */
	void enterMatching(ExamANTLRParser.MatchingContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamANTLRParser#matching}.
	 * @param ctx the parse tree
	 */
	void exitMatching(ExamANTLRParser.MatchingContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExamANTLRParser#matching_tokenA}.
	 * @param ctx the parse tree
	 */
	void enterMatching_tokenA(ExamANTLRParser.Matching_tokenAContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamANTLRParser#matching_tokenA}.
	 * @param ctx the parse tree
	 */
	void exitMatching_tokenA(ExamANTLRParser.Matching_tokenAContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExamANTLRParser#matching_tokenB}.
	 * @param ctx the parse tree
	 */
	void enterMatching_tokenB(ExamANTLRParser.Matching_tokenBContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamANTLRParser#matching_tokenB}.
	 * @param ctx the parse tree
	 */
	void exitMatching_tokenB(ExamANTLRParser.Matching_tokenBContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExamANTLRParser#matching_answers}.
	 * @param ctx the parse tree
	 */
	void enterMatching_answers(ExamANTLRParser.Matching_answersContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExamANTLRParser#matching_answers}.
	 * @param ctx the parse tree
	 */
	void exitMatching_answers(ExamANTLRParser.Matching_answersContext ctx);
}