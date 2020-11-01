import React from 'react';
import {useHistory} from 'react-router-dom';
import NavBar from "./NavBar";
import {Formik} from "formik";
import * as Api from "./ApiRest";
import * as Yup from "yup";
import NotFound from "./NotFound";

export default function ChangePassword() {
    const history = useHistory();
    const username = localStorage.getItem('userName')

    return (
        <>
            {
                (username &&
                <div className="align-content-center">
                    <NavBar/>
                    <Formik
                        initialValues={{oldPassword: '', newPassword: '', passwordConfirmation: ''}}
                        onSubmit={(values, {setSubmitting}) => {
                            setTimeout(() => {
                                const {newPassword} = values
                                Api.changePassword(newPassword)
                                    .then(resp => {
                                        console.log(resp)
                                            if (resp.status === 200) {
                                                history.push('/home')
                                            }
                                        }
                                    )
                                setSubmitting(false);
                            }, 500);
                        }}
                        validationSchema={Yup.object().shape({
                            oldPassword: Yup.string()
                                .required("Old password required.")
                                .matches(localStorage.getItem('password'), 'Old password does not match'),
                            newPassword: Yup.string()
                                .required("No new password provided.")
                                .min(8, "Password is too short - should be 8 chars minimum.")
                                .max(256, 'Password cannot have more than 256 characters')
                                .matches(/(?=.*[0-9])/, "Password must contain a number."),
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
                                            name="oldPassword"
                                            type="password"
                                            placeholder="Enter your old password"
                                            value={values.oldPassword}
                                            onChange={handleChange}
                                            onBlur={handleBlur}
                                            className='form-control'
                                        />
                                        {errors.oldPassword && touched.oldPassword && (
                                            <div className="input-feedback text-danger">{errors.oldPassword}</div>
                                        )}
                                    </div>
                                    <div className="form-group">
                                        <input
                                            name="newPassword"
                                            type="password"
                                            placeholder="Enter your new password"
                                            value={values.newPassword}
                                            onChange={handleChange}
                                            onBlur={handleBlur}
                                            className='form-control'
                                        />
                                        {errors.newPassword && touched.newPassword && (
                                            <div className="input-feedback text-danger">{errors.newPassword}</div>
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
