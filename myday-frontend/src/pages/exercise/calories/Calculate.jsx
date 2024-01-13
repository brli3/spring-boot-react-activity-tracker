import { Form, InputNumber } from "antd";
import { useState } from "react";

const { Item } = Form;

const layout = {
  labelCol: {
    span: 10,
  },
  wrapperCol: {
    span: 20,
  },
};

const calculateCalories = (weightInKg, met, durationInMin) => {
  const calories = (weightInKg * met * durationInMin * 3.5) / 200;
  if (Number.isNaN(calories)) return 0;
  return calories.toFixed(1);
};

function Calculate({ exercise }) {
  const { name, desc, met } = exercise;
  const [weight, setWeight] = useState(0);
  const [duration, setDuration] = useState(0);

  return (
    <div style={{backgroundColor: '#cccc'}}>
      <h4 style={{ marginBottom: 10 }}>How much calories I burned?</h4>
      <p style={{color: 'blue'}}> {name || " "} {desc || " "}
      </p>
      <Form {...layout}>
        <Item name="weigtht" label="Your weight: " style={{ margin: 10 }}>
          <span>
            <InputNumber
              min={0}
              max={300}
              style={{ marginRight: 10 }}
              onChange={(value) => {
                setWeight(value);
              }}
            />
            kg
          </span>
        </Item>
        <Item name="minutes" label="Duration:" style={{ margin: 5 }}>
          <span>
            <InputNumber
              min={0}
              max={1000}
              style={{ marginRight: 10 }}
              onChange={(value) => {
                setDuration(value);
              }}
            />
            min
          </span>
        </Item>
      </Form>
      <h4 style={{ marginTop: 30 }}>
        You burned:
        <span style={{ color: "green", margin: "0 10px 0" }}>
          {calculateCalories(weight, met, duration)}
        </span>
        Cal!
      </h4>
    </div>
  );
}

export default Calculate;
