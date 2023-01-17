import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { userLogin } from "../../../services/UserService";
import { Link } from "react-router-dom";

function UserLogin() {
  const [userName, setUserName] = useState("");
  const [password, setPassword] = useState("");

  const navigate = useNavigate();

  const [authFailed, setAuthFailed] = useState("");
  const [formErrors, setFormErrors] = useState({});

  const handleSubmit = () => {
    let errors = {};

    if (!userName) {
      errors["userNameError"] = "Please enter a valid Username";
    }
    if (!password) {
      errors["passwordError"] = "Please enter a valid Password";
    }
    setFormErrors(errors);

    const noErrors = Object.keys(errors).length === 0;

    if (noErrors) {
      userLogin(userName, password)
        .then((res) => {
        console.log(res)
          localStorage.setItem("loggedInUser", JSON.stringify(res.data));
          navigate("/user/dashboard");
        })
        .catch((error) => {
            setAuthFailed(error.response.data.message)
        });
    }
    window.event.preventDefault();
  };
  return (
    <>
    <h1 style={{margin: "25px 0 15px 0" }}> <b>Optimized Amplifier Placement</b></h1>
      <div class="tab-content">
        <div
          class="tab-pane fade show active"
          id="pills-login"
          role="tabpanel"
          aria-labelledby="tab-login"
        >
          <div style={{ color: "red", paddingBottom: 10 }}>{authFailed}</div>

          <form>
            <div class="text-center mb-3">
              <p>Sign in!</p>
            </div>

            <div class="form-outline mb-4">
              <input
                id="loginName"
                class="form-control"
                value={userName}
                onChange={(e) => setUserName(e.target.value)}
              />
              {formErrors.userNameError && (
                <div style={{ color: "red", paddingBottom: 10 }}>
                  {formErrors.userNameError}{" "}
                </div>
              )}

              <label class="form-label" for="loginName">
                User Name
              </label>
            </div>

            <div class="form-outline mb-4">
              <input
                type="password"
                id="loginPassword"
                class="form-control"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
              />
              {formErrors.passwordError ? (
                <div style={{ color: "red", paddingBottom: 10 }}>
                  {formErrors.passwordError}{" "}
                </div>
              ) : (
                <></>
              )}
              <label class="form-label" for="loginPassword">
                Password
              </label>
            </div>

            <button
              type="submit"
              class="btn btn-outline-primary btn-block mb-4 "
              onClick={handleSubmit}
            >
              Sign in
            </button>

            <hr></hr>
            <div class="text-center">
              Not a member? <br></br>
              <Link
                className="btn btn-outline-primary mb-2"
                aria-current="page"
                to="/user/register"
              >
                Register{" "}
              </Link>
            </div>
          </form>
        </div>
      </div>
    </>
  );
}

export default UserLogin;
