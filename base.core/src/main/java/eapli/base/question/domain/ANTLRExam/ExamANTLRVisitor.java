// Generated from C:/Users/Joao Laptop/OneDrive - Instituto Superior de Engenharia do Porto/Documents/sem4pi-22-23-27/base.core/src/main/java/eapli/base/question/domain\ExamANTLR.g4 by ANTLR 4.12.0
package eapli.base.question.domain.ANTLRExam;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link ExamANTLRParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface ExamANTLRVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link ExamANTLRParser#prog}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProg(ExamANTLRParser.ProgContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExamANTLRParser#exam}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExam(ExamANTLRParser.ExamContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExamANTLRParser#exam_body}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExam_body(ExamANTLRParser.Exam_bodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExamANTLRParser#section}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSection(ExamANTLRParser.SectionContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExamANTLRParser#question}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuestion(ExamANTLRParser.QuestionContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExamANTLRParser#body}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBody(ExamANTLRParser.BodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExamANTLRParser#true_false}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTrue_false(ExamANTLRParser.True_falseContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExamANTLRParser#missing_words}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMissing_words(ExamANTLRParser.Missing_wordsContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExamANTLRParser#missing_answer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMissing_answer(ExamANTLRParser.Missing_answerContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExamANTLRParser#numerical}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumerical(ExamANTLRParser.NumericalContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExamANTLRParser#short_awnser}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShort_awnser(ExamANTLRParser.Short_awnserContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExamANTLRParser#multiple_choice}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiple_choice(ExamANTLRParser.Multiple_choiceContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExamANTLRParser#multiple_choice_option}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiple_choice_option(ExamANTLRParser.Multiple_choice_optionContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExamANTLRParser#matching}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMatching(ExamANTLRParser.MatchingContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExamANTLRParser#matching_tokenA}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMatching_tokenA(ExamANTLRParser.Matching_tokenAContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExamANTLRParser#matching_tokenB}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMatching_tokenB(ExamANTLRParser.Matching_tokenBContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExamANTLRParser#matching_answers}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMatching_answers(ExamANTLRParser.Matching_answersContext ctx);
}