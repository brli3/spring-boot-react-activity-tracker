import { useEffect, useState } from "react";
import { Card, Button, Form, Input, Cascader, Tooltip } from "antd";
import { CloseOutlined } from "@ant-design/icons";
import { reqVisibleMealsBy, reqVisibleMeals } from "../../../api";

const { Item } = Form;

function SelectMeal({ setMeals }) {
  const [allItems, setAllItems] = useState([]);
  const [currentId, setCurrentId] = useState(0);
  const [selectedItems, setSelectedItems] = useState([]);
  const [options, setOptions] = useState([]);
  const [servings, setServings] = useState(0);
  const [form] = Form.useForm();

  const fetchOptions = async () => {
    const items = await reqVisibleMeals();
    const categorySet = [...new Set(items.map((item) => item.category))];
    const options = categorySet.map((category) => ({
      value: category,
      label: category,
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
    const result = await reqVisibleMealsBy("category", targetOption.value);
    targetOption.loading = false; // load options lazily
    targetOption.children = result.map((item) => ({
      value: item.id,
      label: item.name,
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
    item = { ...item, servings: servings };
    setSelectedItems([...selectedItems, item]);
    setMeals([...selectedItems, item]);
    console.log("Added item ", item);
    form.resetFields();
  };

  return (
    <Card>
      <Form
        layout="vertical"
        form={form}
        style={{ maxWidth: 400, margin: "auto" }}
      >
        <Item name="meal" label="Select an meal: " required>
          <Cascader
            options={options}
            loadData={loadData}
            onChange={onChangeOptions}
          />
        </Item>
        <Item
          name="servings"
          label="How many servings (can be a decimal number): "
          required
        >
          <Input
            type="number"
            placeholder="Enter a number, e.g 1 or 1.4"
            addonAfter="servings"
            min={0}
            max={1000}
            onChange={(e) => {
              setServings(e.target.value);
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
            {`${id + 1}. ${item.name} ${item.desc} - ${item.servings} ${
              Number.parseInt(item.servings) === 1 ? "serving" : "servings"
            }`}
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

export default SelectMeal;
