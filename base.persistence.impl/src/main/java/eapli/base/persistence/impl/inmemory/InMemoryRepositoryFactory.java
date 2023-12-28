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
package eapli.base.persistence.impl.inmemory;

import eapli.base.StudentRegistration.repository.StudentRegistrationRepository;
import eapli.base.TeacherRegistration.repository.TeacherRegistrationRepository;
import eapli.base.classe.repository.ClasseRepository;
import eapli.base.clientusermanagement.repositories.ClientUserRepository;
import eapli.base.clientusermanagement.repositories.SignupRequestRepository;
import eapli.base.course.repository.CourseRepository;
import eapli.base.exam.repository.ExamRepository;
import eapli.base.grade.repository.GradeRepository;
import eapli.base.meeting.repository.MeetingRepository;
import eapli.base.profile.repository.ProfileRepository;
import eapli.base.infrastructure.bootstrapers.BaseBootstrapper;
import eapli.base.infrastructure.persistence.RepositoryFactory;
import eapli.base.question.repository.*;
import eapli.base.sharedboard.repository.BoardLockRepository;
import eapli.base.sharedboard.repository.SharedBoardRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.domain.repositories.UserRepository;
import eapli.framework.infrastructure.authz.repositories.impl.inmemory.InMemoryUserRepository;

/**
 *
 * Created by nuno on 20/03/16.
 */
public class InMemoryRepositoryFactory implements RepositoryFactory {

    static {
        // only needed because of the in memory persistence
        new BaseBootstrapper().execute();
    }

    @Override
    public UserRepository users(final TransactionalContext tx) {
        return new InMemoryUserRepository();
    }

    @Override
    public UserRepository users() {
        return users(null);
    }

    @Override
    public ClientUserRepository clientUsers(final TransactionalContext tx) {

        return new InMemoryClientUserRepository();
    }

    @Override
    public ClientUserRepository clientUsers() {
        return clientUsers(null);
    }

    @Override
    public SignupRequestRepository signupRequests() {
        return signupRequests(null);
    }

    @Override
    public ProfileRepository profiles() {
        return new InMemoryProfileRepository(null);
    }

    @Override
    public ProfileRepository profiles(TransactionalContext autoTx) {
        return new InMemoryProfileRepository();
    }

    @Override
    public BoardLockRepository locks() {
        return new InMemoryBoardLockRepository(null);
    }

    @Override
    public BoardLockRepository locks(TransactionalContext autoTx) {
        return new InMemoryBoardLockRepository();
    }

    @Override
    public QuestionRepository questions() {
        return new InMemoryQuestionRepository(null);
    }

    @Override
    public QuestionRepository questions(TransactionalContext autoTx) {
        return new InMemoryQuestionRepository();
    }

    @Override
    public ExamRepository exams() {
        return new InMemoryExamRepository(null);
    }

    @Override
    public ExamRepository exams(TransactionalContext autoTx) {
        return new InMemoryExamRepository();
    }

    @Override
    public CourseRepository courses() {
        return new InMemoryCourseRepository(null);
    }

    @Override
    public CourseRepository courses(TransactionalContext autoTx) {
        return new InMemoryCourseRepository();
    }

    @Override
    public MeetingRepository meetings() {
        return null;
    }

    @Override
    public MeetingRepository meetings(TransactionalContext autoTx) {
        return null;
    }

    @Override
    public ClasseRepository classe() {
        return null;
    }

    @Override
    public ClasseRepository classe(TransactionalContext autoTx) {
        return null;
    }

    @Override
    public GradeRepository grade() {
        return new InMemoryGradeRepository(null);
    }

    @Override
    public GradeRepository grade(TransactionalContext autoTx) {
        return new InMemoryGradeRepository();
    }

    @Override
    public TeacherRegistrationRepository teacherRegistrations(){
        return new InMemoryTeacherRegistrationRepository(null);
    }

    @Override
    public TeacherRegistrationRepository teacherRegistrations(TransactionalContext autoTx){
        return new InMemoryTeacherRegistrationRepository();
    }

    @Override
    public StudentRegistrationRepository studentRegistrations(){
        return new InMemoryStudentRegistrationRepository(null);
    }

    @Override
    public StudentRegistrationRepository studentRegistrations(TransactionalContext autoTx){
        return new InMemoryStudentRegistrationRepository();
    }


    @Override
    public SharedBoardRepository sharedboards() {
        return new InMemorySharedBoardRepository(null);
    }

    @Override
    public SharedBoardRepository sharedboards(TransactionalContext autoTx) {
        return new InMemorySharedBoardRepository();
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
    public ShortAnswerRepository shortAnswers() {
        return null;
    }

    @Override
    public ShortAnswerRepository shortAnswers(TransactionalContext autoTx) {
        return null;
    }

    @Override
    public MatchingRepository matchings() {
        return null;
    }
    @Override
    public MatchingRepository matchings(TransactionalContext autoTx) {
        return null;
    }

    @Override
    public TrueOrFalseRepository trueOrFalses() {
        return null;
    }
    @Override
    public TrueOrFalseRepository trueOrFalses(TransactionalContext autoTx) {
        return null;
    }
    @Override
    public MultipleChoiceRepository multipleChoices() {
        return null;
    }
    @Override
    public MultipleChoiceRepository multipleChoices(TransactionalContext autoTx) {
        return null;
    }
    @Override
    public MissingWordsRepository missingWords() {
        return null;
    }
    @Override
    public MissingWordsRepository missingWords(TransactionalContext autoTx) {
        return null;
    }
    @Override
    public NumericalRepository numericals() {
        return null;
    }
    @Override
    public NumericalRepository numericals(TransactionalContext autoTx) {
        return null;
    }

    @Override
    public SignupRequestRepository signupRequests(final TransactionalContext tx) {
        return new InMemorySignupRequestRepository();
    }

    @Override
    public TransactionalContext newTransactionalContext() {
        // in memory does not support transactions...
        return null;
    }



}
