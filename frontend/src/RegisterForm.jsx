import React from "react";
import {Formik} from "formik";
import * as Yup from "yup";
import {useHistory} from 'react-router-dom';
import * as Api from "./components/ApiRest";

export default function RegisterForm() {
    const history = useHistory();

    return (
        <div>
            <Formik
                initialValues={{email: '', username: '', password: '', passwordConfirmation: ''}}
                onSubmit={(values, {setSubmitting}) => {
                    setTimeout(() => {
                        const {username, email, password} = values
                        Api.register(username, email, password)
                            .then(response => {
                                    if (response.status>=200 && response.status<300) {
                                        history.push(`/successful/${username}`)
                                    }
                                }
                            )
                            .catch(() => "Boom")
                        setSubmitting(false);
                    }, 500);
                }}
                validationSchema={Yup.object().shape({
                    email: Yup.string()
                        .email('Please, insert a valid email')
                        .required("Email is required."),
                    username: Yup.string()
                        .required("Username is required.")
                        .min(6, 'Username is too short - should be 6 chars minimum.')
                        .matches(/^[a-z][a-z0-9]*(?:_[A-Za-z0-9]+)*$/, 'Username must start with a letter and cannot have capitalize letters'),
                    password: Yup.string()
                        .required("No password provided.")
                        .min(8, "Password is too short - should be 8 chars minimum.")
                        .max(256, 'Password cannot have more than 256 characters')
                        .matches(/(?=.*[0-9])/, "Password must contain a number."),
                    passwordConfirmation: Yup.string()
                        .required('Password confirmation is required')
                        .oneOf([Yup.ref('password'), null], 'Password confirmation does not match')
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
                            <div className='bg-dark rounded-lg mb-3'>
                                <h1 className='text-light p-3 mx-5 '>Simple, fast, funny. Welcome to
                                    <span className='logo' onClick={() => history.push('/')}> Agile Tasks</span></h1>
                            </div>
                            <h1 className='text-center font-italic font-weight-bold'>Register now!</h1>
                            <div className="form-group">
                                <input
                                    name="email"
                                    type="email"
                                    placeholder="Enter your email"
                                    value={values.email}
                                    onChange={handleChange}
                                    onBlur={handleBlur}
                                    className='form-control'
                                />
                                {errors.password && touched.password && (
                                    <div className="input-feedback text-danger">{errors.email}</div>
                                )}
                            </div>
                            <div className="form-group">
                                <input
                                    name="username"
                                    type="text"
                                    placeholder="Enter your new username"
                                    value={values.username}
                                    onChange={handleChange}
                                    onBlur={handleBlur}
                                    className='form-control'
                                />
                                {errors.username && touched.username && (
                                    <div className="input-feedback text-danger">{errors.username}</div>
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
                                    <div className="input-feedback text-danger">{errors.passwordConfirmation}</div>
                                )}
                            </div>
                            <button type="submit" className="btn btn-info" disabled={isSubmitting}>
                                Start now!
                            </button>
                        </form>
                    );
                }}
            </Formik>
        </div>

    );
}
