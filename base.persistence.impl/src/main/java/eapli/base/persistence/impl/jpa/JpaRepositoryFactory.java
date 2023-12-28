/*
 * Copyright (c) 2013-2023 the original author or authors.
 *
 * MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package eapli.base.persistence.impl.jpa;

import eapli.base.Application;
import eapli.base.StudentRegistration.repository.StudentRegistrationRepository;
import eapli.base.TeacherRegistration.repository.TeacherRegistrationRepository;
import eapli.base.classe.repository.ClasseRepository;
import eapli.base.clientusermanagement.repositories.SignupRequestRepository;
import eapli.base.course.repository.CourseRepository;
import eapli.base.exam.repository.ExamRepository;
import eapli.base.grade.repository.GradeRepository;
import eapli.base.meeting.repository.MeetingRepository;
import eapli.base.profile.repository.ProfileRepository;
import eapli.base.infrastructure.persistence.RepositoryFactory;
import eapli.base.question.repository.*;
import eapli.base.sharedboard.repository.BoardLockRepository;
import eapli.base.sharedboard.repository.SharedBoardRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.domain.repositories.UserRepository;
import eapli.framework.infrastructure.authz.repositories.impl.jpa.JpaAutoTxUserRepository;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

/**
 *
 * Created by nuno on 21/03/16.
 */
public class JpaRepositoryFactory implements RepositoryFactory {

    @Override
    public UserRepository users(final TransactionalContext autoTx) {
        return new JpaAutoTxUserRepository(autoTx);
    }

    @Override
    public UserRepository users() {
        return new JpaAutoTxUserRepository(Application.settings().getPersistenceUnitName(),
                Application.settings().getExtendedPersistenceProperties());
    }

    @Override
    public JpaClientUserRepository clientUsers(final TransactionalContext autoTx) {
        return new JpaClientUserRepository(autoTx);
    }

    @Override
    public JpaClientUserRepository clientUsers() {
        return new JpaClientUserRepository(Application.settings().getPersistenceUnitName());
    }
    @Override
    public JpaShortAnswersRepository shortAnswers(){
        return new JpaShortAnswersRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public JpaShortAnswersRepository shortAnswers(final TransactionalContext autoTx){
        return new JpaShortAnswersRepository(autoTx);
    }
    @Override
    public JpaMatchingRepository matchings(){
        return new JpaMatchingRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public JpaMatchingRepository matchings(final TransactionalContext autoTx){
        return new JpaMatchingRepository(autoTx);
    }

    @Override
    public JpaTrueOrFalseRepository trueOrFalses(){
        return new JpaTrueOrFalseRepository(Application.settings().getPersistenceUnitName());
    }
    @Override
    public JpaTrueOrFalseRepository trueOrFalses(final TransactionalContext autoTx){
        return new JpaTrueOrFalseRepository(autoTx);
    }
    @Override
    public JpaMultipleChoiceRepository multipleChoices(){
        return new JpaMultipleChoiceRepository(Application.settings().getPersistenceUnitName());
    }
    @Override
    public JpaMultipleChoiceRepository multipleChoices(final TransactionalContext autoTx){
        return new JpaMultipleChoiceRepository(autoTx);
    }
    @Override
    public JpaMissingWordsRepository missingWords(){
        return new JpaMissingWordsRepository(Application.settings().getPersistenceUnitName());
    }
    @Override
    public JpaNumericalRepository numericals(final TransactionalContext autoTx){
        return new JpaNumericalRepository(autoTx);
    }
    @Override
    public JpaNumericalRepository numericals(){
        return new JpaNumericalRepository(Application.settings().getPersistenceUnitName());
    }
    @Override
    public JpaMissingWordsRepository missingWords(final TransactionalContext autoTx){
        return new JpaMissingWordsRepository(autoTx);
    }
    @Override
    public SignupRequestRepository signupRequests(final TransactionalContext autoTx) {
        return new JpaSignupRequestRepository(autoTx);
    }

    @Override
    public SignupRequestRepository signupRequests() {
        return new JpaSignupRequestRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public ProfileRepository profiles() {
        return new JpaProfileRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public ProfileRepository profiles(TransactionalContext autoTx) {
        return new JpaProfileRepository(autoTx);
    }

    @Override
    public BoardLockRepository locks() {
        return new JpaBoardLockRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public BoardLockRepository locks(TransactionalContext autoTx) {
        return new JpaBoardLockRepository(autoTx);
    }

    @Override
    public QuestionRepository questions() {
        return new JpaQuestionRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public QuestionRepository questions(TransactionalContext autoTx) {
        return new JpaQuestionRepository(autoTx);
    }

    @Override
    public ExamRepository exams() {
        return new JpaExamRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public ExamRepository exams(TransactionalContext autoTx) {
        return new JpaExamRepository(autoTx);
    }

    @Override
    public CourseRepository courses() {
        return new JpaCourseRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public CourseRepository courses(TransactionalContext autoTx) {
        return new JpaCourseRepository(autoTx);
    }

    @Override
    public TeacherRegistrationRepository teacherRegistrations(){
        return new JpaTeacherRegistrationRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public TeacherRegistrationRepository teacherRegistrations(TransactionalContext autoTx){
        return new JpaTeacherRegistrationRepository(autoTx);
    }

    @Override
    public StudentRegistrationRepository studentRegistrations(){
        return new JpaStudentRegistrationRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public StudentRegistrationRepository studentRegistrations(TransactionalContext autoTx){
        return new JpaStudentRegistrationRepository(autoTx);
    }

    @Override
    public ExamRepository headers() {
        return null;
    }

    @Override
    public ExamRepository headers(TransactionalContext autoTx) {
        return null;
    }

    @Override
    public TransactionalContext newTransactionalContext() {
        return JpaAutoTxRepository.buildTransactionalContext(Application.settings().getPersistenceUnitName(),
                Application.settings().getExtendedPersistenceProperties());
    }
    @Override
    public MeetingRepository meetings() {
        return new JpaMeetingRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public MeetingRepository meetings(TransactionalContext autoTx) {
        return new JpaMeetingRepository(autoTx);
    }

    public SharedBoardRepository sharedboards() {
        return new JpaSharedBoardRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public SharedBoardRepository sharedboards(TransactionalContext autoTx) {
        return  new JpaSharedBoardRepository(autoTx);
    }

    @Override
    public ClasseRepository classe() {
        return new JpaClasseRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public ClasseRepository classe(TransactionalContext autoTx) {
        return new JpaClasseRepository(autoTx);
    }

    @Override
    public GradeRepository grade() {
        return new JpaGradeRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public GradeRepository grade(TransactionalContext autoTx) {
        return new JpaGradeRepository(autoTx);
    }



}
