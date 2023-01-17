import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { registerUser } from "../../../services/UserService";

function RegisterUser() {
  const [userName, setUserName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const navigate = useNavigate();

  const [formErrors, setFormErrors] = useState({});

  const handleSubmit = () => {
    let errors = {};

    if (!userName) {
      errors["userNameError"] = "Please enter a valid Username";
    }
    if (!email) {
      //!email.includes('@')
      errors["emailError"] = "Please enter a valid Email";
    }
    if (!password) {
      errors["passwordError"] = "Please enter a valid Password";
    }

    setFormErrors(errors);

    const noErrors = Object.keys(errors).length === 0;

    if (noErrors) {
      const payload = {
        userName: userName,
        email: email,
        password: password,
      };

      registerUser(payload).then((res) => {
        alert("New User Registered with Name " + res.data);
       navigate('/')
      });
    }
  };

  return (
    <>
      <section class="vh-100">
        <div class="container h-100">
          <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col-lg-12 col-xl-11">
              <div class="card text-black">
                <div class="card-body p-md-5">
                  <div class="row justify-content-center">
                    <div class="col-md-10 col-lg-6 col-xl-5 order-2 order-lg-1">
                      <p class="text-center h1 fw-bold mb-5 mx-1 mx-md-4 mt-4">
                        Sign up
                      </p>

                      <form class="mx-1 mx-md-4">
                        <div class="d-flex flex-row align-items-center mb-4">
                        <i class="fas fa-user fa-lg me-3 fa-fw"></i>
                          <div class="form-outline flex-fill mb-0">
                            <input
                              id="userName"
                              class="form-control"
                              value={userName}
                              onChange={(e) => setUserName(e.target.value)}
                            />
                            {formErrors.userNameError && (
                              <div style={{ color: "red", paddingBottom: 10 }}>
                                {" "}
                                {formErrors.userNameError}
                              </div>
                            )}
                            <label class="form-label" for="userName">
                              User Name
                            </label>
                          </div>
                        </div>

                        <div class="d-flex flex-row align-items-center mb-4">                
                          <i class="fas fa-envelope fa-lg me-3 fa-fw"></i>
                          <div class="form-outline flex-fill mb-0">
                            <input
                              id="email"
                              class="form-control"
                              value={email}
                              onChange={(e) => setEmail(e.target.value)}
                            />
                            {formErrors.emailError && (
                              <div style={{ color: "red", paddingBottom: 10 }}>
                                {" "}
                                {formErrors.emailError}
                              </div>
                            )}
                            <label class="form-label" for="email">
                              Email
                            </label>
                          </div>
                        </div>

                        <div class="d-flex flex-row align-items-center mb-4">
                          <i class="fas fa-lock fa-lg me-3 fa-fw"></i>
                          <div class="form-outline flex-fill mb-0">
                            <input
                              type="password"
                              id="password"
                              class="form-control"
                              value={password}
                              onChange={(e) => setPassword(e.target.value)}
                            />
                            {formErrors.passwordError && (
                              <div style={{ color: "red", paddingBottom: 10 }}>
                                {" "}
                                {formErrors.passwordError}
                              </div>
                            )}
                            <label class="form-label" for="password">
                              Password
                            </label>
                          </div>
                        </div>

                        <div class="d-flex justify-content-center mx-4 mb-3 mb-lg-4">
                          <button
                            type="button"
                            class="btn btn-outline-primary btn-lg"
                            onClick={handleSubmit}
                          >
                            Register
                          </button>
                          <button
                            type="button"
                            class="btn btn-outline-danger btn-lg"
                            onClick={()=> {navigate(-1)}}
                          >
                            Cancel
                          </button>
                        </div>
                      </form>
                    </div>
                    <div class="col-md-10 col-lg-6 col-xl-7 d-flex align-items-center order-1 order-lg-2">
                      <img
                        src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-registration/draw1.webp"
                        class="img-fluid"
                      />
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </section>
    </>
  );
}

export default RegisterUser;
