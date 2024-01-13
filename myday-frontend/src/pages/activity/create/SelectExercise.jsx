import { useEffect, useState } from "react";
import { Card, Button, Form, Input, Cascader, Tooltip } from "antd";
import { CloseOutlined } from "@ant-design/icons";
import { reqVisibleExercisesBy, reqVisibleExercises } from "../../../api";

const { Item } = Form;

function SelectExercise({ setExercises }) {
  const [allItems, setAllItems] = useState([]);
  const [currentId, setCurrentId] = useState(0);
  const [selectedItems, setSelectedItems] = useState([]);
  const [options, setOptions] = useState([]);
  const [duration, setDuration] = useState(0);
  const [form] = Form.useForm();

  const fetchOptions = async () => {
    const items = await reqVisibleExercises();
    const nameSet = [...new Set(items.map((e) => e.name))];
    const options = nameSet.map((name) => ({
      value: name,
      label: name,
      isLeaf: false,
    }));
    setOptions(options);
    setAllItems([...items]);
  };

  useEffect(() => {
    fetchOptions();
  }, []);

  const loadData = async (selectedOptions) => {
    const targetOption = selectedOptions[0];
    targetOption.loading = true; // load options lazily

    const result = await reqVisibleExercisesBy("name", targetOption.value);
    targetOption.children = result.map((item) => ({
      value: item.id,
      label: item.desc,
      isLeaf: true,
    }));
    setOptions([...options]);
  };

  const onChangeOptions = (_, selectedOptions) => {
    const selectedId =
      selectedOptions.length > 1 ? selectedOptions[1].value : null;
    if (selectedId) {
      setCurrentId(selectedId);
    }
  };

  const onClickAdd = () => {
    if (!currentId) return;
    let item = allItems.find((item) => item.id === currentId);
    item = { ...item, duration };
    setSelectedItems([...selectedItems, item]);
    setExercises([...selectedItems, item]);
    console.log("Add item ", item);
    form.resetFields();
  };

  return (
    <Card>
      <Form
        layout="vertical"
        form={form}
        style={{ maxWidth: 400, margin: "auto" }}
      >
        <Item name="exercise" label="Select an exercise: " required>
          <Cascader
            options={options}
            loadData={loadData}
            onChange={onChangeOptions}
          />
        </Item>
        <Item name="duration" label="How long the exercise was: " required>
          <Input
            type="number"
            placeholder="Enter a number"
            addonAfter="min"
            min={0}
            max={1000}
            onChange={(e) => {
              setDuration(e.target.value);
            }}
          />
        </Item>
        <Button type="primary" style={{ margin: 10 }} onClick={onClickAdd}>
          Add One
        </Button>
      </Form>
      <ul>
        {selectedItems.map((item, id) => (
          <li key={id}>
            {`${id + 1}. ${item.name} ${item.desc} - ${item.duration} min`}
            <Tooltip title="Remove">
              <Button
                type="text"
                danger
                icon={<CloseOutlined />}
                onClick={() => {
                  const newItems = selectedItems.filter(
                    (itemObj) => itemObj.id !== item.id
                  );
                  setSelectedItems([...newItems]);
                }}
              ></Button>
            </Tooltip>
          </li>
        ))}
      </ul>
    </Card>
  );
}

export default SelectExercise;
