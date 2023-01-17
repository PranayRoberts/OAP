import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { addCard } from "../../../services/NodeService";

function AddCard() {
  const [cardName, setCardName] = useState("");
  const [card_type, setCardType] = useState("");

  const [formErrors, setFormErrors] = useState({});

  const navigate = useNavigate();
  function addCardAxios(payload) {
    addCard(payload).then((res) => {
      console.log(res);
      alert("New Card Added");
    });
  }

  const handleSubmit = () => {
    let errors = {};

    if (!cardName) {
      errors["nameError"] = "Please enter a valid Card Name";
    }
    if (!card_type) {
      errors["typeError"] = "Please enter a valid Card Type";
    }

    setFormErrors(errors);

    const noErrors = Object.keys(errors).length === 0;

    if (noErrors) {
      const payload = {
        cardName: cardName,
        card_type: card_type,
      };

      addCardAxios(payload);
    }
  };

  return (
    <div>
      <div className="title">
        <h2>Please Enter the Card details</h2>
      </div>
      <div className="body">
        <form>
          <input
            className="input"
            id="cardName"
            value={cardName}
            onChange={(e) => setCardName(e.target.value)}
            placeholder="Card Name"
          />
          {formErrors.nameError && (
            <div style={{ color: "red", paddingBottom: 10 }}>
              {" "}
              {formErrors.nameError}
            </div>
          )}

          <input
            className="input"
            id="card_type"
            value={card_type}
            onChange={(e) => setCardType(e.target.value)}
            placeholder="Card Type"
          />
          {formErrors.typeError && (
            <div style={{ color: "red", paddingBottom: 10 }}>
              {" "}
              {formErrors.typeError}
            </div>
          )}

          {/* <div className="drop-down-cont flex justify-content-space-between align-items-center">
            <label htmlFor="card_type">Card Type</label>
            <select id="card_type" className="input">
              <option
                value="card_type"
                onChange={(e) => setCardType(e.target.value)}
              >
                Booster
              </option>
              <option
                value="card_type"
                onChange={(e) => setCardType(e.target.value)}
              >
                Prebooster
              </option>
            </select>
          </div> */}

          <br />
          <button
            type="button"
            class="btn btn-outline-primary"
            onClick={handleSubmit}
          >
            Add Card
          </button>

          <button
              type="button"
              class="btn btn-outline-danger"
              onClick= {() => { navigate(-1)}}
            >
              Cancel
            </button>
        </form>
      </div>
    </div>
  );
}

export default AddCard;
