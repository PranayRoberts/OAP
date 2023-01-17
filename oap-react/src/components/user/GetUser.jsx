import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { getUserByName } from "../../services/UserService";

function GetUser() {
  const [user, SetUser] = useState(null);
  const { userName } = useParams();

  useEffect(() => {
    getUserByName(userName).then((res) => {
      SetUser(res.data);
    });
  }, [userName]);

  return (
    <div>
      {user != null && (
        <section class="mh-100 overflow-hidden">
          <div class="py-2 h-100">
            <div class="row d-flex justify-content-center align-items-center h-100">
              <div class="col col-lg-6 mb-4 mb-lg-0">
                <div class="card">
                  <div class="row g-0">
                    <div class="col-md-4 gradient-custom text-center text-white">
                      <img
                        src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-chat/ava1-bg.webp"
                        alt="Avatar"
                        class="img-fluid my-4"
                      />
                      <h5 className="text-black">{user.userName}</h5>
                    </div>

                    <div class="col-md-8">
                      <div class="card-body p-4">
                        <h6>Information</h6>
                        <hr class="mt-0 mb-4"></hr>

                        <div class="row pt-1">
                          <div class="col-6 mb-3">
                            <h6>Name</h6>
                            <p class="text-muted">{user.userName}</p>
                          </div>
                          <div class="col-6 mb-3">
                            <h6>Email</h6>
                            <p class="text-muted">{user.email}</p>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </section>
      )}
    </div>
  );
}

export default GetUser;
