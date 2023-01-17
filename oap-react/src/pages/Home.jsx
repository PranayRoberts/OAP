import React from "react";

import UserLogin from "../components/user/login/UserLogin";

function Home() {
  return (
    <section class="vh-100">
      <div class="container-fluid h-custom">
        <div class="row d-flex justify-content-center align-items-center h-100">
          <div class="col-md-9 col-lg-6 col-xl-5">
            <img
              src="https://img.freepik.com/free-vector/happy-woman-chatting-with-friends-online_74855-14073.jpg?w=740&t=st=1671358809~exp=1671359409~hmac=9e98f89e28b7f1a3b9988d779515e8735ec8bebee9a57a899c397fe74398a882"
              class="img-fluid"
              alt="Sample"
            />
          </div>
          <div class="col-md-8 col-lg-6 col-xl-4 offset-xl-1">
            <form>
              <div class="tab-content">
                <div
                  class="tab-pane fade show active"
                  id="pills-login-user"
                  role="tabpanel"
                  aria-labelledby="tab-login"
                >
                  <UserLogin />
                </div>
              </div>
            </form>
          </div>
        </div>
      </div>
    </section>
  );
}

export default Home;
