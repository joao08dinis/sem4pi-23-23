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
package eapli.base.infrastructure.persistence;

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
import eapli.base.question.repository.*;
import eapli.base.sharedboard.repository.BoardLockRepository;
import eapli.base.sharedboard.repository.SharedBoardRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.domain.repositories.UserRepository;

/**
 * @author Paulo Gandra Sousa
 *
 */
public interface RepositoryFactory {

    /**
     * factory method to create a transactional context to use in the repositories
     *
     * @return
     */
    TransactionalContext newTransactionalContext();

    /**
     *
     * @param autoTx
     *            the transactional context to enrol
     * @return
     */
    UserRepository users(TransactionalContext autoTx);

    /**
     * repository will be created in auto transaction mode
     *
     * @return
     */
    UserRepository users();

    /**
     *
     * @param autoTx
     *            the transactional context to enroll
     * @return
     */
    ClientUserRepository clientUsers(TransactionalContext autoTx);

    /**
     * repository will be created in auto transaction mode
     *
     * @return
     */
    ClientUserRepository clientUsers();

    ShortAnswerRepository shortAnswers();

    ShortAnswerRepository shortAnswers(TransactionalContext autoTx);

    MultipleChoiceRepository multipleChoices();

    MultipleChoiceRepository multipleChoices(TransactionalContext autoTx);

    MissingWordsRepository missingWords();

    MissingWordsRepository missingWords(TransactionalContext autoTx);

    NumericalRepository numericals();

    NumericalRepository numericals(TransactionalContext autoTx);

    /**
     *
     * @param autoTx
     *            the transactional context to enroll
     * @return
     */
    SignupRequestRepository signupRequests(TransactionalContext autoTx);

    /**
     * repository will be created in auto transaction mode
     *
     * @return
     */
    SignupRequestRepository signupRequests();

    /**
     * repository will be created in auto transaction mode
     *
     * @return
     */
    ProfileRepository profiles();

    /**
     *
     * @param autoTx
     *            the transactional context to enroll
     * @return
     */
    ProfileRepository profiles(TransactionalContext autoTx);

    BoardLockRepository locks();

    BoardLockRepository locks(TransactionalContext autoTx);

    /**
     * repository will be created in auto transaction mode
     *
     * @return
     */
    QuestionRepository questions();

    /**
     *
     * @param autoTx
     *            the transactional context to enroll
     * @return
     */
    QuestionRepository questions(TransactionalContext autoTx);

    /**
     * repository will be created in auto transaction mode
     *
     * @return
     */
    ExamRepository exams();

    /**
     *
     * @param autoTx
     *            the transactional context to enroll
     * @return
     */
    ExamRepository exams(TransactionalContext autoTx);

    /**
     * repository will be created in auto transaction mode
     *
     * @return
     */
    CourseRepository courses();

    /**
     *
     * @param autoTx
     *            the transactional context to enroll
     * @return
     */
    CourseRepository courses(TransactionalContext autoTx);

    /**
     * repository will be created in auto transaction mode
     *
     * @return
     */
    MeetingRepository meetings();
    /**
     *
     * @param autoTx
     *            the transactional context to enroll
     * @return
     */
    MeetingRepository meetings(TransactionalContext autoTx);

    StudentRegistrationRepository studentRegistrations();

    StudentRegistrationRepository studentRegistrations(TransactionalContext autoTx);

    SharedBoardRepository sharedboards();

    SharedBoardRepository sharedboards(TransactionalContext autoTx);

    /**
     * repository will be created in auto transaction mode
     *
     * @return
     */
    ExamRepository headers();

    /**
     *
     * @param autoTx
     *            the transactional context to enroll
     * @return
     */
    ExamRepository headers(TransactionalContext autoTx);

    TeacherRegistrationRepository teacherRegistrations();

    TeacherRegistrationRepository teacherRegistrations(TransactionalContext autoTx);

    ClasseRepository classe();
    ClasseRepository classe(TransactionalContext autoTx);

    GradeRepository grade();
    GradeRepository grade(TransactionalContext autoTx);

    MatchingRepository matchings();
    MatchingRepository matchings(TransactionalContext autoTx);

    TrueOrFalseRepository trueOrFalses();

    TrueOrFalseRepository trueOrFalses(TransactionalContext autoTx);
}
