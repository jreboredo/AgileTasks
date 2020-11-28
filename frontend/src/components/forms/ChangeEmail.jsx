import React,{useEffect} from 'react';
import {useHistory} from 'react-router-dom';
import NavBar from "../NavBar";
import {Formik} from "formik";
import * as Api from "../ApiRest";
import * as Yup from "yup";
import NotFound from "../NotFound";

export default function ChangeEmail() {
    const history = useHistory();
    const username = localStorage.getItem('userName')

    useEffect( () => {
        document.body.style="background-image: var(--img-background-home);" +
            "background-size: 145rem;"
    })

    return (
        <>
            {
                (username &&
                    <div className="align-content-center">
                        <NavBar background={'bg-dark'} showTitle={true}/>
                        <Formik
                            initialValues={{newEmail: '', password: '', passwordConfirmation: ''}}
                            onSubmit={(values, {setSubmitting}) => {
                                setTimeout(() => {
                                    const {newEmail} = values
                                    Api.changeEmail(newEmail)
                                        .then(resp => {
                                                console.log(resp)
                                                if (resp.status === 200) {
                                                    localStorage.removeItem('email');
                                                    localStorage.setItem('email', newEmail);
                                                    history.push('/home');
                                                }
                                            }
                                        )
                                    setSubmitting(false);
                                }, 500);
                            }}
                            validationSchema={Yup.object().shape({
                                newEmail: Yup.string()
                                    .email('Please, insert a valid email')
                                    .required("New email is required."),
                                password: Yup.string()
                                    .required("Password is required.")
                                    .matches(localStorage.getItem('password'), 'Password does not match'),
                                passwordConfirmation: Yup.string()
                                    .required('Password confirmation is required')
                                    .oneOf([Yup.ref('newPassword'), null], 'Password confirmation does not match')
                            })}
                        >
                            {props => {
                                const {
                                    values,
                                    touched,
                                    errors,
                                    isSubmitting,
                                    handleChange,
                                    handleBlur,
                                    handleSubmit
                                } = props;
                                return (
                                    <form className="formulario card m-5 p-3 bg-light " onSubmit={handleSubmit}>
                                        <h1 className='text-center font-italic font-weight-bold'>Change password!</h1>
                                        <div className="form-group">
                                            <input
                                                name="email"
                                                type="email"
                                                placeholder="Enter your new email"
                                                value={values.newEmail}
                                                onChange={handleChange}
                                                onBlur={handleBlur}
                                                className='form-control'
                                            />
                                            {errors.newEmail && touched.newEmail && (
                                                <div className="input-feedback text-danger">{errors.newEmail}</div>
                                            )}
                                        </div>
                                        <div className="form-group">
                                            <input
                                                name="password"
                                                type="password"
                                                placeholder="Enter your password"
                                                value={values.password}
                                                onChange={handleChange}
                                                onBlur={handleBlur}
                                                className='form-control'
                                            />
                                            {errors.password && touched.password && (
                                                <div className="input-feedback text-danger">{errors.password}</div>
                                            )}
                                        </div>
                                        <div className="form-group">
                                            <input
                                                name="passwordConfirmation"
                                                type="password"
                                                placeholder="Confirm password"
                                                value={values.passwordConfirmation}
                                                onChange={handleChange}
                                                onBlur={handleBlur}
                                                className='form-control'
                                            />
                                            {errors.passwordConfirmation && touched.passwordConfirmation && (
                                                <div
                                                    className="input-feedback text-danger">{errors.passwordConfirmation}</div>
                                            )}
                                        </div>
                                        <button type="submit" className="btn btn-info" disabled={isSubmitting}>
                                            Change now!
                                        </button>
                                    </form>
                                );
                            }}
                        </Formik>

                    </div>
                ) || <NotFound/>}
        </>
    );
}
